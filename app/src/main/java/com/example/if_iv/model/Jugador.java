package com.example.if_iv.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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

//    public Jugador(String nombre, String capitulo, Date comienzoCap, int puntos) {
//        this.nombre = nombre;
//        this.capitulo = capitulo;
//        this.comienzoCap = comienzoCap;
//        this.puntos = puntos;
//    }


//    DateFormat format= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//    String da= format.format(new Date());
    public Jugador(String nombre, String capitulo, String comienzoCAp, int puntos) {
        this.nombre = nombre;
        this.capitulo = capitulo;
        this.puntos = puntos;

        DateFormat format= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date= format.format(new Date());

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

}
