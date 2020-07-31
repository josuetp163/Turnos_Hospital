package ec.edu.espol.common;

import ec.edu.espol.constants.Especialidad;
import ec.edu.espol.constants.Genero;
import java.io.Serializable;
import java.util.Objects;
import java.util.PriorityQueue;

public class UsrMedico extends Usuario implements Serializable {
    private Especialidad especialidad;
    private Puesto puesto;
    private String usuario;
    private String contra;
    private PriorityQueue<Turno> turnos;

    public UsrMedico(String n, String a, int e, Genero g, Especialidad esp, String usr, String cont) {
        super(n,a,e,g);
        especialidad = esp;
        usuario = usr;
        contra = cont;
        turnos = new PriorityQueue<>((Turno t1, Turno t2)->t1.getPacienteAtender().getSintoma().getPrioridad() - t2.getPacienteAtender().getSintoma().getPrioridad());
    }

    public UsrMedico(String n, String a, int e, Genero g, Especialidad esp, String usr, String cont, Puesto p) {
        super(n,a,e,g);
        especialidad = esp;
        usuario = usr;
        contra = cont;
        puesto = p;
        turnos = new PriorityQueue<>((Turno t1, Turno t2)->t2.getPacienteAtender().getSintoma().getPrioridad() - t1.getPacienteAtender().getSintoma().getPrioridad());
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Turno siguienteTurno(){
        return turnos.peek();
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public PriorityQueue<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(PriorityQueue<Turno> turnos) {
        this.turnos = turnos;
    }

    public boolean verificarUsuario(String usuario, String contrasenia){
        return (this.usuario.equals(usuario)) && (this.contra.equals(contrasenia));
    }

    public String getUsuario(){
        return usuario;
    }

    public String getContraseña(){
        return contra;
    }

    @Override
    public String toString() {
        return nombre+" "+apellido;
    }

    public String infoText(){
        String line = nombre + "|" + apellido + "|" + edad + "|" + genero.toString() + "|" + especialidad.toString() + "|" + usuario + "|" + contra + "|" + puesto + "|" ;
        if(turnos.size()>=1){
            for(Turno t: turnos){
                String infoPaciente = t.getPacienteAtender().infoText() + "-";
                line+=infoPaciente;
            }
        }else{
            line+="null";
        }
        return line;
    }

    public String infoTextTurno(){
        return nombre + "," + apellido + "," + edad + "," + genero.toString() + "," + especialidad.toString() + "," + usuario + "," + contra;
    }

    public String infoCompleta() {
        return "UsrMedico{" +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", pacientes=" + turnos.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null){
            if(obj instanceof UsrMedico){
                UsrMedico m = (UsrMedico)obj;
                if(usuario.equals(m.usuario))
                    return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(especialidad, puesto, usuario, contra, turnos);
    }
}
