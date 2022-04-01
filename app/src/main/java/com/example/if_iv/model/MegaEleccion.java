package com.example.if_iv.model;

import com.example.if_iv.util.Boolean01;

public class MegaEleccion
{

    private String decision;
    private boolean hecha;
    private String rutaFic; //Escena extra o capitulo correspondiente siguiente

    public MegaEleccion(String decision, boolean hecha, String rutaFic) {
        this.decision = decision;
        this.hecha = hecha;
        this.rutaFic = rutaFic;
    }

    public MegaEleccion(String decision, int hecha, String rutaFic) {
        this.decision = decision;
        this.hecha = Boolean01.gestionInt(hecha);
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
