package ec.edu.espol.views;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;


public class ConfirmStage {
    private Stage stage;
    private Alert alerta;

    public ConfirmStage(Stage stg){
        stage = stg;
        crearEstructura();
    }

    private void crearEstructura(){
        alerta = new Alert(Alert.AlertType.CONFIRMATION,"");
        alerta.initStyle(StageStyle.TRANSPARENT);
        alerta.getDialogPane().setPrefSize(400,200);
        alerta.initOwner(stage);
    }

    public boolean confirmar(String msg){
        alerta.getDialogPane().setContentText("Desea "+msg);
        alerta.getDialogPane().setHeaderText("");
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.get() == ButtonType.OK){
            return true;
        }else if(result.get() == ButtonType.CANCEL){
            return false;
        }
        return false;
    }
}
