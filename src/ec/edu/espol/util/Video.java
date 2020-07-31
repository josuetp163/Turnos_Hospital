package ec.edu.espol.util;

public class Video {

    private String nombre;
    private String media;
    private Long duracion;

    public Video(String n, String uri, long d) {
        nombre = n;
        media = uri;
        duracion = d;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Video{" +
                "nombre='" + nombre + '\'' +
                ", media='" + media + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
