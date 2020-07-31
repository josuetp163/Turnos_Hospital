package ec.edu.espol.main;

import ec.edu.espol.constants.Constantes;
import ec.edu.espol.system.SysData;
import ec.edu.espol.util.HiloTurnos;
import ec.edu.espol.util.HiloVideos;
import ec.edu.espol.util.Hora;
import ec.edu.espol.util.LeerEscribirDatos;
import ec.edu.espol.views.MainScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static final Hora hora = new Hora();
    public static final Thread hilo= new Thread(hora);
    public static final HiloVideos videos = new HiloVideos(LeerEscribirDatos.cargarVideos());
    public static final Thread hiloVideos= new Thread(videos);
    public static final HiloTurnos turnos = new HiloTurnos();
    public static final Thread hiloTurnos = new Thread(turnos);
    MainScene mainScene;
    SysData sysData;


    @Override
    public void stop() throws Exception {
        hilo.stop();
        hiloVideos.stop();
        hiloTurnos.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        sysData = new SysData();
        mainScene = new MainScene(primaryStage);
        hiloVideos.start();
        hilo.start();
        hiloTurnos.start();
        primaryStage.getIcons().add(new Image("/ec/edu/espol/media/logo.png"));
        setUserAgentStylesheet(Constantes.RUTASTYLE);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
