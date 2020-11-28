package model;

public class Comentario {

    private int id;
    private Usuario usuario;
    private String comentario;
    private Comercio comercio;
    private float puntaje;
    private Respuesta respuesta;

    public Comentario(Usuario usuario, String comentario, Comercio c, float puntaje) {
        this.id = -1;
        this.usuario = usuario;
        this.comentario = comentario;
        this.comercio = c;
        this.puntaje = puntaje;
    }

    public Comentario(Usuario usuario, String comentario, Comercio c) {
        this.id = -1;
        this.usuario = usuario;
        this.comentario = comentario;
        this.comercio = c;
    }

    public Comentario(int id, Usuario usuario, String comentario, Comercio c) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
        this.comercio = c;
    }

    public Comentario(int id, Usuario usuario, String comentario, Comercio c, float puntaje) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
        this.comercio = c;
        this.puntaje = puntaje;
    }

    public Comentario(int id, Usuario usuario, String comentario, Comercio c, Respuesta r) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
        this.comercio = c;
        this.respuesta = r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio c) {
        this.comercio = c;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }

    public float getPuntaje() {
        return this.puntaje;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public Respuesta getRespuesta() {
        return this.respuesta;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", usuario=" + usuario + ", comentario=" + comentario;
    }

}
