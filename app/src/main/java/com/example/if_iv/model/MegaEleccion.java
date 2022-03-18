package com.example.if_iv.model;

public class MegaEleccion
{

    private String decision;
    private boolean hecha;
    private String rutaFic; //Escena extra o capitulo correspondiente siguiente

    public MegaEleccion(String rutaFic) {
        this.rutaFic = rutaFic;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public boolean isHecha() {
        return hecha;
    }

    public void setHecha(boolean hecha) {
        this.hecha = hecha;
    }

    public String getRutaFic() {
        return rutaFic;
    }

    public void setRutaFic(String rutaFic) {
        this.rutaFic = rutaFic;
    }
}
