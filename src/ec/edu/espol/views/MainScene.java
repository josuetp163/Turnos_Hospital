package ec.edu.espol.views;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainScene {
    public static Stage primaryStage;
    public static Scene scene;
    public static final InicioView inicio = new InicioView();
    public static final MedicoView medicoView = new MedicoView();
    public static final ConsultaView consultaView = new ConsultaView();
    public static final FormPacienteView formPacienteView = new FormPacienteView();
    public static final FormMedicoView formMedicoView = new FormMedicoView();
    public static final LoginView loginView = new LoginView();
    public static final TurnosView turnosView = new TurnosView();
    public static final PuestosView puestosView = new PuestosView();
    public static final NuevoPuestoView nuevoPuestoView = new NuevoPuestoView();
    public static final AsignarEditarPuestoView asignarEditarPuestoView = new AsignarEditarPuestoView();
    public static final EliminarPuestoView eliminarPuestoView = new EliminarPuestoView();
    public static final DesasignarPuestoView desasignarPuestoView = new DesasignarPuestoView();
    public static ConfirmStage confirmStage;
    private static double x=0;
    private static double y=0;

    private MainScene(){
        //Hide public constructor
    }
    public MainScene(Stage stage){
        primaryStage = stage;
        scene = new Scene(inicio.getRoot(),1200,600);
        primaryStage.setTitle("INICIO");
        primaryStage.setScene(scene);
        allowDrag();
        confirmStage = new ConfirmStage(stage);
    }

    protected static void allowDrag(){
        scene.getRoot().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        scene.getRoot().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
            }
        });
    }




}
