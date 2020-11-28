package model;

public class DTOComentariosComercios {

    private String nombre, descripcion;
    private int cantidadComentarios;

    public DTOComentariosComercios(String nombre, String descripcion, int cantidadComentarios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadComentarios = cantidadComentarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(int cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }

    @Override
    public String toString() {
        return "DTOComentariosComercios{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", cantidadComentarios=" + cantidadComentarios + '}';
    }

}
