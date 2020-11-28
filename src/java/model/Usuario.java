package model;

public class Usuario {
    private int id_usuario;
    private String usuario, nombre, contrasena;

    public Usuario(int id_usuario, String usuario, String nombre, String contrasena) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
    
    public Usuario(String usuario, String nombre, String contrasena) {
        this.id_usuario = -1;
        this.usuario = usuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return usuario;
    }
    
}
