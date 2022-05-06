package com.example.if_iv.model;

import java.util.ArrayList;
import java.util.Objects;

public class Capitulo
{

    private String nombre;
    private String rutaFic;
    private boolean hecho;

    private ArrayList<Capitulo> hijos;

    public Capitulo(String nombre, String rutaFic, boolean hecho) {
        this.nombre = nombre;
        this.rutaFic = rutaFic;
        this.hecho = hecho;
    }

    public Capitulo(String nombre)
    {
        this.nombre= nombre;
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

    public ArrayList<Capitulo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Capitulo> hijos) {
        this.hijos = hijos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capitulo capitulo = (Capitulo) o;
        return Objects.equals(nombre, capitulo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
