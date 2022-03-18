package com.example.if_iv.model;

public class Capitulo
{

    private String nombre;
    private String rutaFic;
    private boolean hecho;

    public Capitulo(String nombre, String rutaFic, boolean hecho) {
        this.nombre = nombre;
        this.rutaFic = rutaFic;
        this.hecho = hecho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaFic() {
        return rutaFic;
    }

    public void setRutaFic(String rutaFic) {
        this.rutaFic = rutaFic;
    }

    public boolean isHecho() {
        return hecho;
    }

    public void setHecho(boolean hecho) {
        this.hecho = hecho;
    }
}
