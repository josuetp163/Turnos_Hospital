package ec.edu.espol.common;

import java.io.Serializable;

public class Sintoma implements Serializable {

    private String nombre;
    private int prioridad;

    public Sintoma(String n, int p) {
        nombre = n;
        prioridad = p;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return nombre.toUpperCase();
    }
}
