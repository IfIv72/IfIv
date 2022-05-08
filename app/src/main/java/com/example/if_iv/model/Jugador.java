package com.example.if_iv.model;

import com.example.if_iv.util.MegaClase;

import java.util.Date;

//CREATE TABLE "Jugador" (
//        "nombre"	TEXT,
//        "capitulo"	TEXT,
//        "comienzoCap"	TEXT,
//        "puntos"	INTEGER
//        );

public class Jugador
{

    private String nombre;
    private String capitulo; //Proceso
    private Date comienzoCap; //Fecha comienzo capitulo
    private int puntos; //Puntos que tiene
    private Date preguntas;

//    public Jugador(String nombre, String capitulo, Date comienzoCap, int puntos) {
//        this.nombre = nombre;
//        this.capitulo = capitulo;
//        this.comienzoCap = comienzoCap;
//        this.puntos = puntos;
//    }


    public Jugador(String nombre, String capitulo, String comienzoCap, int puntos, String preguntas) {
        this.nombre = nombre;
        this.capitulo = capitulo;
        this.puntos = puntos;
        this.comienzoCap= MegaClase.deStringAFecha(comienzoCap);
        this.preguntas= MegaClase.deStringAFecha(preguntas);
    }

    public Jugador(String nombre, String capitulo, Date comienzoCap, int puntos, Date preguntas) {
        this.nombre = nombre;
        this.capitulo = capitulo;
        this.puntos = puntos;
        this.comienzoCap= comienzoCap;
        this.preguntas= preguntas;
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

    public Date getComienzoCap() {
        return comienzoCap;
    }

    public void setComienzoCap(Date comienzoCap) {
        this.comienzoCap = comienzoCap;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Date getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Date preguntas) {
        this.preguntas = preguntas;
    }
}
