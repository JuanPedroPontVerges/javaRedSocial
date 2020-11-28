package model;

import java.util.ArrayList;

public class Comercio {

    private int id;
    private String nombre;
    private Rubro rubro;
    private String descripcion;
    private ArrayList<Comentario> comentarios;

    public Comercio(int id, String nombre, Rubro rubro, String descripcion, ArrayList<Comentario> comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.rubro = rubro;
        this.descripcion = descripcion;
    }

    public Comercio(int id, String nombre, Rubro rubro, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.rubro = rubro;
        this.descripcion = descripcion;
    }

    public Comercio(String nombre, Rubro rubro, String descripcion) {
        this.id = -1;
        this.nombre = nombre;
        this.rubro = rubro;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Comentario> getComentario() {
        return this.comentarios;
    }

    public void setComentario(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Comercio{" + "id=" + id + ", nombre=" + nombre + ", rubro=" + rubro + ", descripcion=" + descripcion + '}';
    }

}
