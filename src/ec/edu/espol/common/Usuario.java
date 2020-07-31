package ec.edu.espol.common;

import ec.edu.espol.constants.Genero;

import java.io.Serializable;

public class Usuario implements Serializable {

    protected String nombre;
    protected String apellido;
    protected int edad;
    protected Genero genero;

    public Usuario(String nombre, String apellido, int edad, Genero genero){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
