package ec.edu.espol.views;

import ec.edu.espol.common.Consulta;
import ec.edu.espol.common.UsrPaciente;
import ec.edu.espol.system.SysController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MedicoView implements View{
    private BorderPane root;
    public static Label lblHora;
    private static Label lblNombreMedico;
    private Button btnSgtTurno;
    private Button btnCerrarSesion;

        public MedicoView () {
            crearEstructura();

        }

        private void crearEstructura(){
            root = new BorderPane();
            crearBotones();
            crearLabels();
            AnchorPane rowBottom = new AnchorPane();
            AnchorPane.setLeftAnchor(btnCerrarSesion,250d);
            AnchorPane.setRightAnchor(btnSgtTurno ,250d);
            rowBottom.getChildren().addAll(btnCerrarSesion,btnSgtTurno);
            VBox center = new VBox();
            center.getChildren().addAll(lblNombreMedico,rowBottom);
            VBox hora = new VBox(lblHora);
            hora.setAlignment(Pos.CENTER_RIGHT);
            center.setAlignment(Pos.CENTER);
            root.setTop(hora);
            root.setCenter(center);

        }

        private void crearBotones(){
            btnSgtTurno = new Button("SIGUIENTE TURNO");
            btnSgtTurno.setOnAction(e->{
                Consulta c = SysController.siguienteTurno();
                if(c != null){
                    UsrPaciente p = c.getPaciente();
                    Platform.runLater(()->{
                        MainScene.consultaView.setInformation(p.getNombre(),p.getApellido(),String.valueOf(p.getEdad()),p.getGenero(),p.getSintoma());
                        MainScene.allowDrag();
                    });
                    MainScene.scene.setRoot(MainScene.consultaView.getRoot());
                }
            });
            btnCerrarSesion = new Button("CERRAR SESION");
            btnCerrarSesion.setOnAction(e->{
                if(MainScene.confirmStage.confirmar("cerrar sesion?")) {
                    MainScene.scene.setRoot(MainScene.inicio.getRoot());
                    MainScene.allowDrag();
                }
            });
        }
        private static void crearLabels(){
            lblHora = new Label("");
            lblHora.setId("lblHora");
            lblNombreMedico = new Label("Nombre del Medico");
            lblNombreMedico.setId("lblNombreMedico");

        }

        public BorderPane getRoot(){
            return root;
        }

        public void setLblMedico(String usuario){
            lblNombreMedico.setText(usuario);
        }
}
