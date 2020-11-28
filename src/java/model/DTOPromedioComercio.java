package model;

public class DTOPromedioComercio {

    private Comercio comercio;
    private float promedio;

    public DTOPromedioComercio(Comercio comercio, float promedio) {
        this.comercio = comercio;
        this.promedio = promedio;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "DTOPromedioComercio{" + "comercio=" + comercio + ", promedio=" + promedio + '}';
    }

}
