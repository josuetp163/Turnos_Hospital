package ec.edu.espol.common;

import java.io.Serializable;

public class Turno implements Serializable {

    private UsrMedico medicoAsignado;
    private UsrPaciente pacienteAtender;
    private Puesto puestoAsignado;
    private int turnoAsignado;
    private static int turnosTotales;

    public Turno(UsrMedico m, UsrPaciente p) {
        medicoAsignado = m;
        pacienteAtender = p;
        puestoAsignado = m.getPuesto();
        turnoAsignado = turnosTotales+1;
        turnosTotales+=1;
    }

    //Solo para lectura de archivo
    public Turno(UsrMedico m, UsrPaciente p, int ta) {
        medicoAsignado = m;
        pacienteAtender = p;
        puestoAsignado = m.getPuesto();
        turnoAsignado = ta;
        turnosTotales = ta+1;
    }

    public UsrMedico getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(UsrMedico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    public UsrPaciente getPacienteAtender() {
        return pacienteAtender;
    }

    public void setPacienteAtender(UsrPaciente pacienteAtender) {
        this.pacienteAtender = pacienteAtender;
    }

    public Puesto getPuestoAsignado() {
        return puestoAsignado;
    }

    public void setPuestoAsignado(Puesto puestoAsignado) {
        this.puestoAsignado = puestoAsignado;
    }

    public int getTurnoAsignado() {
        return turnoAsignado;
    }

    public void setTurnoAsignado(int turnoAsignado) {
        this.turnoAsignado = turnoAsignado;
    }


    @Override
    public String toString() {
        return String.valueOf(turnoAsignado);
    }

    public String infoText(){
        return medicoAsignado.getUsuario() + "|" + pacienteAtender.infoText() + "|" + turnoAsignado;
    }

    public String test(){
        return "Turno: " + String.valueOf(turnoAsignado) + ", Puesto: " + puestoAsignado.toString();
    }
}
