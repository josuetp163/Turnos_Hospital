package ec.edu.espol.views;

import ec.edu.espol.constants.Constantes;
import ec.edu.espol.main.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InicioView implements View{
    private VBox root;
    private Image img;
    private ImageView imagen;
    private Button btnTurnos;
    private Button btnFormPaciente;
    private Button btnSisMedico;
    private Button btnFormMedico;
    private Button btnPuestos;
    private Button btnSalir;

    public InicioView(){
        crearEstructura();
    }

    public void crearEstructura(){
        root = new VBox();
        crearBotones();
        File file = new File(Constantes.RUTALOGO); //
        img = new Image(file.toURI().toString());
        imagen = new ImageView(img);
        imagen.setId("imagen");
        root.getChildren().add(imagen);
        VBox column1 = new VBox();
        column1.getChildren().addAll(btnTurnos,btnSisMedico,btnPuestos);
        VBox column2 = new VBox();
        column2.getChildren().addAll(btnFormPaciente,btnFormMedico,btnSalir);
        HBox row1 = new HBox();
        row1.getChildren().addAll(column1,column2);
        row1.setAlignment(Pos.CENTER);
        root.getChildren().addAll(row1);
        root.setAlignment(Pos.CENTER);
    }

    public void crearBotones(){
        // image = new Image();
        crearBtnTurno();
        crearBtnFormPaciente();
        crearBtnSisMedico();
        crearBtnCrearMedico();
        crearBtnSalir();
        crearBtnPuestos();
    }

    public void crearBtnTurno(){
        btnTurnos = new Button("TURNOS");
        btnTurnos.setOnAction(e->{
            MainScene.scene.setRoot(MainScene.turnosView.getRoot());
            Main.videos.iniciarVideo();
            MainScene.allowDrag();
        });
    }
    public void crearBtnFormPaciente(){
        btnFormPaciente = new Button("FORMULARIO PACIENTE");
        btnFormPaciente.setOnAction(e ->{
            Platform.runLater(()->{
                MainScene.scene.setRoot(MainScene.formPacienteView.getRoot());
                MainScene.allowDrag();
            });
        });
    }
    public void crearBtnSisMedico(){
        btnSisMedico = new Button("SISTEMA MEDICO");
        btnSisMedico.setOnAction(e->{
            MainScene.scene.setRoot(MainScene.loginView.getRoot());
            MainScene.allowDrag();
        });
    }

    public void crearBtnCrearMedico(){
        btnFormMedico = new Button("FORMULARIO MEDICO");
        btnFormMedico.setOnAction(e->{
            MainScene.scene.setRoot(MainScene.formMedicoView.getRoot());
            MainScene.allowDrag();
        });
    }

    public void crearBtnPuestos(){
        btnPuestos = new Button("PUESTOS");
        btnPuestos.setOnAction(e ->{
            Platform.runLater(()->{
                MainScene.scene.setRoot(MainScene.puestosView.getRoot());
                MainScene.allowDrag();
            });
        });
    }
    public void crearBtnSalir(){
        btnSalir = new Button("SALIR");
        btnSalir.setOnAction(e->{
            if(MainScene.confirmStage.confirmar("salir del sistema?")) {
                Platform.exit();
            }
        });
    }

    public VBox getRoot(){
        return root;
    }
}
