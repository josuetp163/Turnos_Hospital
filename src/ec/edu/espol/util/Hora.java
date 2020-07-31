package ec.edu.espol.util;

import ec.edu.espol.views.*;
import javafx.application.Platform;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hora implements Runnable{

    Date objDate;
    boolean stop;
        
    @Override
    public void run() {
        while(!stop) {
            objDate = new Date();
            String strDateFormat = "hh:mm:ss";
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            Platform.runLater(()->{ConsultaView.lblHora.setText(objSDF.format(objDate));
            FormMedicoView.lblHora.setText(objSDF.format(objDate));
            FormPacienteView.lblHora.setText(objSDF.format(objDate));
            MedicoView.lblHora.setText(objSDF.format(objDate));
            LoginView.lblHora.setText(objSDF.format(objDate));
            MedicoView.lblHora.setText(objSDF.format(objDate));
            TurnosView.lblHora.setText(objSDF.format(objDate));
            NuevoPuestoView.lblHora.setText(objSDF.format(objDate));
            AsignarEditarPuestoView.lblHora.setText(objSDF.format(objDate));
            EliminarPuestoView.lblHora.setText(objSDF.format(objDate));
            PuestosView.lblHora.setText(objSDF.format(objDate));
            PuestosView.lblHora.setText(objSDF.format(objDate));});
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopThread() {
        stop = true;
    }

}
