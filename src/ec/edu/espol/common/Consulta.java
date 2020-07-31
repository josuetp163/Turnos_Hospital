package ec.edu.espol.common;

import java.io.Serializable;

public class Consulta implements Serializable {
    private UsrMedico medico;
    private UsrPaciente paciente;
    private String diagnostico;
    private String receta;

    public Consulta(UsrMedico medico, UsrPaciente paciente){
        this.medico = medico;
        this.paciente = paciente;
    }

    public void setDiagnostico(String dig){
        diagnostico = dig;
    }

    public void setReceta(String rec){
        receta = rec;
    }

    public UsrMedico getMedico() {
        return medico;
    }

    public UsrPaciente getPaciente() {
        return paciente;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getReceta() {
        return receta;
    }
}
