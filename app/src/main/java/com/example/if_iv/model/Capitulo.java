package com.example.if_iv.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Capitulo
{

    private String nombre;
    private String rutaFic;
    private boolean hecho;

    private List<Capitulo> hijos;
    private String siguiente;
    private String padre;

    public Capitulo(String nombre, String rutaFic, boolean hecho, String siguiente, String padre) {
        this.nombre = nombre;
        this.rutaFic = rutaFic;
        this.hecho = hecho;
        this.siguiente = siguiente;
        this.padre = padre;
    }

    public Capitulo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Capitulo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Capitulo> hijos) {
        this.hijos = hijos;
    }

    public String getSiguiente() {
        return siguiente;
    }

    public String getRutaFic() {
        return rutaFic;
    }

    public String getPadre() {
        return padre;
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

    @Override
    public String toString() {
        return "Capitulo " + nombre + "{ hijos : [" + ( hijos == null ? "" : hijos) + "], siguiente : '" + siguiente + "'}";
    }
}
