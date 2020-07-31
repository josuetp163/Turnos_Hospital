package ec.edu.espol.views;

import ec.edu.espol.common.UsrMedico;
import ec.edu.espol.system.SysController;
import ec.edu.espol.system.SysData;
import javafx.application.Platform;
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

public class NuevoPuestoView {
    private BorderPane root;
    private static Label lblInicioSesion;
    public static Label lblHora;
    private static Label lblPuesto;
    private TextField txtPuesto;
    private static Label lblMedico;
    private ComboBox comboMedico;
    private Button btnCancelar;
    private Button btnAgregarPuesto;

    public NuevoPuestoView(){
        crearEstructura();

    }

    private void crearEstructura(){
        root = new BorderPane();
        crearBotones();
        creatTxtFields();
        crearLabels();
        AnchorPane top = new AnchorPane();
        AnchorPane.setLeftAnchor(lblInicioSesion,10d);
        AnchorPane.setRightAnchor(lblHora,10d);
        top.getChildren().addAll(lblInicioSesion,lblHora);
        root.setTop(top);
        AnchorPane bottom = new AnchorPane();
        AnchorPane.setLeftAnchor(btnCancelar,10d);
        AnchorPane.setRightAnchor(btnAgregarPuesto,10d);
        bottom.getChildren().addAll(btnCancelar,btnAgregarPuesto);
        root.setBottom(bottom);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(lblPuesto,0,0);
        grid.add(txtPuesto,1,0);
        grid.add(lblMedico,0,1);
        grid.add(comboMedico,1,1);
        root.setCenter(grid);
    }

    private void crearBotones(){
        btnCancelar = new Button("CANCELAR");
        btnCancelar.setOnAction(e->{
            txtPuesto.clear();
            MainScene.scene.setRoot(MainScene.puestosView.getRoot());
            MainScene.allowDrag();
        });
        btnAgregarPuesto = new Button("AGREGAR PUESTO");
        btnAgregarPuesto.setOnAction(e->{
            if(MainScene.confirmStage.confirmar("agregar el nuevo puesto?") &&
                    SysController.crearPuesto((UsrMedico) comboMedico.getSelectionModel().getSelectedItem())) {
                    txtPuesto.clear();
                    MainScene.scene.setRoot(MainScene.puestosView.getRoot());
                    MainScene.allowDrag();
            }
        });
    }

    private void creatTxtFields(){
        txtPuesto = new TextField();
        txtPuesto.setEditable(false);
        comboMedico = new ComboBox(FXCollections.observableList(SysData.getMedicosSinPuestos()));
    }

    private static void crearLabels(){
        lblInicioSesion = new Label("Agregar Puesto");
        lblHora = new Label("12:30");
        lblPuesto = new Label("Nuevo Puesto:");
        lblMedico = new Label("Medico Asignado: ");
        instanciarIDs();
    }

    public BorderPane getRoot(){
        return root;
    }

    private static void instanciarIDs(){
        lblHora.setId("lblHora");
        lblInicioSesion.setId("lblPaciente");
    }

    public TextField getTxtPuesto() {
        return txtPuesto;
    }

    public void actualizarCombos(){
        Platform.runLater(()->{
            comboMedico.setItems(FXCollections.observableList(SysData.getMedicosSinPuestos()));
        });
    }
}
