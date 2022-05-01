package com.example.if_iv.model;

public class Pregunta {

    private String pregunta, op1, op2, correcta;

    public Pregunta(String pregunta, String op1, String op2, String correcta) {
        this.pregunta = pregunta;
        this.op1 = op1;
        this.op2 = op2;
        this.correcta = correcta;
    }

    public String getPregunta() {  return pregunta; }
    public String getOp1() {  return op1; }
    public String getOp2() { return op2;  }
    public String getCorrecta() {   return correcta; }
}
