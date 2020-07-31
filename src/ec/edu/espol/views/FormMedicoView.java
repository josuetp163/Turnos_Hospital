package ec.edu.espol.views;

import ec.edu.espol.constants.Especialidad;
import ec.edu.espol.constants.Genero;
import ec.edu.espol.system.SysController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class FormMedicoView implements View{
    private BorderPane root;
    private static Label lblFormMedico;
    public static Label lblHora;
    private static Label lblNombre;
    private static TextField txtNombre;
    private static Label lblApellido;
    private static TextField txtApellido;
    private static Label lblEdad;
    private static TextField txtEdad;
    private static Label lblGenero;
    private ComboBox comboGenero;
    private static Label lblEspecialidad;
    private ComboBox comboEspecialidad;
    private static Label lblUsuario;
    private static TextField txtUsuario;
    private static Label lblPassword;
    private static PasswordField txtPassword;
    private Button btnCancelar;
    private Button btnTerminar;

    public FormMedicoView(){
        crearEstructura();

    }

    private void crearEstructura(){
        root = new BorderPane();
        crearBotones();
        creatTxtFields();
        crearLabels();
        crearComboBoxs();
        AnchorPane top = new AnchorPane();
        AnchorPane.setLeftAnchor(lblFormMedico,10d);
        AnchorPane.setRightAnchor(lblHora,10d);
        top.getChildren().addAll(lblFormMedico,lblHora);
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
        grid.add(lblEspecialidad,0,4);
        grid.add(comboEspecialidad,1,4);
        grid.add(lblUsuario,0,5);
        grid.add(txtUsuario,1,5);
        grid.add(lblPassword,0,6);
        grid.add(txtPassword,1,6);
        root.setCenter(grid);
    }

    private void crearBotones(){
        btnCancelar = new Button("CANCELAR");
        btnCancelar.setOnAction(e->{
            if(MainScene.confirmStage.confirmar("cancelar el Formulario?")){
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
                    SysController.addMedico(txtNombre.getText(),txtApellido.getText(),txtEdad.getText(),(Genero) comboGenero.getSelectionModel().getSelectedItem(),(Especialidad)comboEspecialidad.getSelectionModel().getSelectedItem(),txtUsuario.getText(),txtPassword.getText())) {
                    txtNombre.clear();
                    txtApellido.clear();
                    txtEdad.clear();
                    txtPassword.clear();
                    txtUsuario.clear();
                    MainScene.scene.setRoot(MainScene.inicio.getRoot());
                    MainScene.allowDrag();
            }
        });
    }

    private static void creatTxtFields(){
        txtEdad = new TextField();
        txtNombre = new TextField();
        txtApellido = new TextField();
        txtUsuario = new TextField();
        txtPassword = new PasswordField();

    }

    private static void crearLabels(){
        lblFormMedico = new Label("Formulario Medico");
        lblHora = new Label("12:30");
        lblNombre = new Label("Nombre:");
        lblApellido = new Label("Apellido: ");
        lblEdad = new Label("Edad:");
        lblGenero = new Label("Genero:");
        lblUsuario = new Label("Usuario:");
        lblEspecialidad = new Label("Especialidad: ");
        lblPassword = new Label("Contraseña: ");
        instanciarIDs();
    }

    private void crearComboBoxs(){
        comboGenero = new ComboBox(FXCollections.observableArrayList(Genero.values()));
        comboEspecialidad = new ComboBox(FXCollections.observableArrayList(Especialidad.values()));
    }

    public BorderPane getRoot(){
        return root;
    }

    private static void instanciarIDs(){
        lblHora.setId("lblHora");
        lblFormMedico.setId("lblPaciente");
        txtPassword.setId("lblPassword");
    }
}
