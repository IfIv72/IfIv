package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.Capitulo;
import com.example.if_iv.util.MegaClase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//findAll
//find
public class CapituloDao {

    //BBDD
    private SQLiteDatabase db;

    //Constructor donde crea el acceso a la BBDD
    public CapituloDao(BBDDSQLiteHelper helper)
    {
        db = helper.getWritableDatabase();
    }

    //Devuelve todos los Capitulos de la BBDD
    public List<Capitulo> findAll()
    {
        ArrayList<Capitulo> capitulos = new ArrayList<>();

        Cursor c=db.rawQuery("select codigo, nombre, rutaFic, hecho from Capitulo order by nombre", null);
        if(c.moveToFirst())
        {
            do {
                Log.d("findAll", "findAll: " + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
                String codigo = c.getString(0);
                String nombre = c.getString(1);
                String rutaFic = c.getString(2);
                Integer h = c.getInt(3);
                boolean hecho = MegaClase.gestionInt(h);

                Capitulo capitulo = new Capitulo(codigo, nombre, rutaFic, hecho);

                capitulos.add(capitulo);

            }while(c.moveToNext());
            c.close();
        }

        return capitulos;
    }

    //Devuelve el capitulo que tenga el mismo nombre que el Capitulo que se le pasa como parametro
    //Devuelve null si no lo encuentra
    public Capitulo find(Capitulo cap)
    {
        Capitulo capitulo=null;
        Cursor c=db.rawQuery("select codigo, nombre, rutaFic, hecho from Capitulo where nombre=?", new String[]{cap.getNombre()});
        if(c.moveToFirst())
        {
                String codigo = c.getString(0);
                String nombre=c.getString(1);
                String rutaFic=c.getString(2);
                Integer h=c.getInt(3);
                boolean hecho= MegaClase.gestionInt(h);

                capitulo = new Capitulo(codigo, nombre, rutaFic, hecho);
        }
        c.close();

        return capitulo;
    }

    public List<Capitulo> findHijos(Capitulo cap)
    {
        LinkedList<Capitulo> hijos = new LinkedList<>();

        Cursor c=db.rawQuery("select codigo, nombre, rutaFic, hecho from Capitulo where padre = '" + cap.getNombre() + "' order by nombre", null);
        if(c.moveToFirst())
        {
            do {
                String codigo=c.getString(0);
                String nombre=c.getString(1);
                String rutaFic=c.getString(2);
                Integer h=c.getInt(3);
                boolean hecho= MegaClase.gestionInt(h);

                Capitulo capitulo = new Capitulo(codigo, nombre, rutaFic, hecho);
                hijos.add(capitulo);

            } while(c.moveToNext());
            c.close();
        }

        return hijos;
    }

}
