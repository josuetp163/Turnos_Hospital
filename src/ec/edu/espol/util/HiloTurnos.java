package ec.edu.espol.util;

import ec.edu.espol.system.SysData;
import ec.edu.espol.views.TurnosView;

public class HiloTurnos implements Runnable{
    private boolean stop;

    @Override
    public void run() {
        while(!stop){
            SysData.actualizarTurnos();
            TurnosView.actualizarTurnos();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
