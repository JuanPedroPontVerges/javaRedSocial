package model;

public class DTOValoracionesPorPuntaje {

    private int puntaje, cantidad;

    public DTOValoracionesPorPuntaje(int puntaje, int cantidad) {
        this.puntaje = puntaje;
        this.cantidad = cantidad;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DTOValoracionesPorPuntaje{" + "puntaje=" + puntaje + ", cantidad=" + cantidad + '}';
    }

}
