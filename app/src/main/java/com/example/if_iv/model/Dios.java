package com.example.if_iv.model;

public class Dios {

    private String nombre;
    private int afinidad;
    private String info;
    private String rutaImg;
    private String mitologia;

    public Dios(String nombre, int afinidad, String info, String rutaImg, String mitologia) {
        this.nombre = nombre;
        this.afinidad = afinidad;
        this.info = info;
        this.rutaImg = rutaImg;
        this.mitologia = mitologia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAfinidad() {
        return afinidad;
    }

    public void setAfinidad(int afinidad) {
        this.afinidad = afinidad;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public String getMitologia() {
        return mitologia;
    }

    public void setMitologia(String mitologia) {
        this.mitologia = mitologia;
    }
}
