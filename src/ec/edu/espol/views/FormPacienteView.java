package ec.edu.espol.views;

import ec.edu.espol.common.Sintoma;
import ec.edu.espol.constants.Especialidad;
import ec.edu.espol.constants.Genero;
import ec.edu.espol.system.SysController;
import ec.edu.espol.system.SysData;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class FormPacienteView implements View{
    private BorderPane root;
    private static Label lblFormPaciente;
    public static Label lblHora;
    private static Label lblNombre;
    private TextField txtNombre;
    private static Label lblApellido;
    private TextField txtApellido;
    private static Label lblEdad;
    private TextField txtEdad;
    private static Label lblGenero;
    private ComboBox comboGenero;
    private static Label lblSintoma;
    private ComboBox comboSintoma;
    //private static Label lblEspecialidad;
    //private ComboBox comboEspecialidad;
    private Button btnCancelar;
    private Button btnTerminar;

    public FormPacienteView(){
        crearEstructura();
    }

    public void crearEstructura(){
        root = new BorderPane();
        crearBotones();
        creatTxtFields();
        crearLabels();
        crearComboBoxs();
        AnchorPane top = new AnchorPane();
        AnchorPane.setLeftAnchor(lblFormPaciente,10d);
        AnchorPane.setRightAnchor(lblHora,10d);
        top.getChildren().addAll(lblFormPaciente,lblHora);
        root.setTop(top);
        AnchorPane bottom = new AnchorPane();
        AnchorPane.setLeftAnchor(btnCancelar,10d);
        AnchorPane.setRightAnchor(btnTerminar,10d);
        bottom.getChildren().addAll(btnCancelar,btnTerminar);
        root.setBottom(bottom);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(lblNombre,0,0);
        grid.add(txtNombre,1,0);
        grid.add(lblApellido,0,1);
        grid.add(txtApellido,1,1);
        grid.add(lblEdad,0,2);
        grid.add(txtEdad,1,2);
        grid.add(lblGenero,0,3);
        grid.add(comboGenero,1,3);
        grid.add(lblSintoma,0,4);
        grid.add(comboSintoma,1,4);
        //grid.add(lblEspecialidad,0,5);
        //grid.add(comboEspecialidad,1,5);
        root.setCenter(grid);
    }

    private void crearBotones(){
        btnCancelar = new Button("CANCELAR");
        btnCancelar.setOnAction(e->{
            if(MainScene.confirmStage.confirmar("cancelar el Formulario?")) {
                txtNombre.clear();
                txtApellido.clear();
                txtEdad.clear();
                MainScene.scene.setRoot(MainScene.inicio.getRoot());
                MainScene.allowDrag();
            }
        });
        btnTerminar = new Button("TERMINAR");
        btnTerminar.setOnAction(e->{
            if(MainScene.confirmStage.confirmar("terminar el Formulario?") &&
                    SysController.addPaciente(txtNombre.getText(),txtApellido.getText(),txtEdad.getText(),(Genero)comboGenero.getSelectionModel().getSelectedItem(),(Sintoma) comboSintoma.getSelectionModel().getSelectedItem())) {
                    txtNombre.clear();
                    txtApellido.clear();
                    txtEdad.clear();
                    MainScene.scene.setRoot(MainScene.inicio.getRoot());
                    MainScene.allowDrag();
            }
        });
    }

    private void creatTxtFields(){
        txtEdad = new TextField();
        txtNombre = new TextField();
        txtApellido = new TextField();


    }

    private static void crearLabels(){
        lblFormPaciente = new Label("Formulario Paciente");
        lblHora = new Label("12:30");
        lblNombre = new Label("Nombre:");
        lblApellido = new Label("Apellido: ");
        lblEdad = new Label("Edad:");
        lblGenero = new Label("Genero:");
        lblSintoma = new Label("Sintoma:");
        //lblEspecialidad = new Label("Especialidad");
        instanciarIDs();
    }

    private void crearComboBoxs(){
        comboGenero = new ComboBox(FXCollections.observableArrayList(Genero.values()));
        comboSintoma = new ComboBox(FXCollections.observableList(SysData.getSintomasActuales()));
        //comboEspecialidad = new ComboBox(FXCollections.observableArrayList(Especialidad.values()));
    }

    public BorderPane getRoot(){
        return root;
    }

    private static void instanciarIDs(){
        lblHora.setId("lblHora");
        lblFormPaciente.setId("lblPaciente");
    }
}
