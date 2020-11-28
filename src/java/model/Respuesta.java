package model;

public class Respuesta {

    private int id;
    private String respuesta;

    public Respuesta(int id, String respuesta) {
        this.id = id;
        this.respuesta = respuesta;
    }

    public Respuesta(String respuesta) {
        this.id = -1;
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "id=" + id + ", respuesta=" + respuesta + '}';
    }

}
