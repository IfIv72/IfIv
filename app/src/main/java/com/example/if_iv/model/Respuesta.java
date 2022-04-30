package com.example.if_iv.model;

public class Respuesta {

    public String texto, siguiente, consecuencia;
    public char tipoCon;

    public Respuesta(String txt, char tipo, String con, String sig)
    {
        texto = txt;
        siguiente = sig;
        tipoCon = tipo;
        consecuencia = con;
    }

    public String getTexto() { return texto;  }

    public String getSiguiente() { return siguiente; }

    public String getConsecuencia() { return consecuencia; }

    public char getTipoCon() { return tipoCon; }
}
