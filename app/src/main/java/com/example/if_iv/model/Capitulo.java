package com.example.if_iv.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Capitulo
{

    private String codigo;
    private String nombre;
    private String rutaFic;
    private boolean hecho;

    public Capitulo(String codigo, String nombre, String rutaFic, boolean hecho) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.rutaFic = rutaFic;
        this.hecho = hecho;
    }

    public Capitulo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRutaFic() {
        return rutaFic;
    }

    public String getCodigo() {
        return codigo;
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
        return "Capitulo [" + codigo + "] : " + nombre;
    }
}
