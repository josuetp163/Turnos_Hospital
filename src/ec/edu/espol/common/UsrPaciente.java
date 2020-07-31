package ec.edu.espol.common;

import ec.edu.espol.constants.Genero;

import java.io.Serializable;

public class UsrPaciente extends Usuario implements Serializable {

    private Sintoma sintoma;

    public UsrPaciente(String n, String a, int e, Genero g, Sintoma s) {
        super(n,a,e,g);
        sintoma = s;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }

    public String infoText(){
        return nombre + "," + apellido + "," + edad + "," + genero + "," + sintoma.getNombre() + "," + sintoma.getPrioridad();
    }
}
