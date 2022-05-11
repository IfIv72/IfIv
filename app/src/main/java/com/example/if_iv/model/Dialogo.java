package com.example.if_iv.model;

public class Dialogo {

    private String cod, hablante, texto, estado, codSiguiente;
    private char tipo;   // d: dialogo normal ,  e: eleccion
    private Respuesta resp1, resp2, resp3;

    public Dialogo(String cod, char tipo, String hablante, String est, String texto, String s)
    {
        this.cod = cod;
        this.hablante = hablante;
        this.texto = texto;
        this.tipo = tipo;
        this.estado = est;
        this.codSiguiente = s;
    }

    public Dialogo(String cod, char tipo, String hablante, Respuesta r1, Respuesta r2, Respuesta r3)
    {
        this.cod = cod;
        this.hablante = hablante;
        this.tipo = tipo;
        this.resp1 = r1;
        this.resp2 = r2;
        this.resp3 = r3;
    }

    public String getCod() { return cod; }

    public String getHablante() { return hablante; }

    public String getEstado() { return estado; }

    public char getTipo() { return tipo; }

    public String getTexto() { return texto; }

    public String getSiguiente() { return codSiguiente; }

    public Respuesta getResp1() { return resp1; }

    public Respuesta getResp2() { return resp2; }

    public Respuesta getResp3() { return resp3; }

}
