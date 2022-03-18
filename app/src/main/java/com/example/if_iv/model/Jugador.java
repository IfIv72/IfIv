package com.example.if_iv.model;

import java.util.Date;

public class Jugador
{

    private String nombre;
    private String capitulo; //Proceso
    private Date comienzoCAp; //Fecha comienzo capitulo
    private int puntos; //Puntos que tiene

    public Jugador(String nombre, String capitulo, Date comienzoCAp, int puntos) {
        this.nombre = nombre;
        this.capitulo = capitulo;
        this.comienzoCAp = comienzoCAp;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public Date getComienzoCAp() {
        return comienzoCAp;
    }

    public void setComienzoCAp(Date comienzoCAp) {
        this.comienzoCAp = comienzoCAp;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
