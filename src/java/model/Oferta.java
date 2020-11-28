package model;

public class Oferta {

    private int id_oferta;
    private String titulo, descripcion;
    private float precio;
    private Comercio comercio;

    public Oferta(int id_oferta, String titulo, String descripcion, float precio, Comercio comercio) {
        this.id_oferta = id_oferta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.comercio = comercio;
    }

    public Oferta(String titulo, String descripcion, float precio, Comercio comercio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.comercio = comercio;
    }

    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    @Override
    public String toString() {
        return titulo;
    }

}
