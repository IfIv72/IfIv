package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.util.Boolean01;
import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.Capitulo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//findAll
//find
public class CapituloDao {

    //BBDD
    private SQLiteDatabase db;

    //Constructor donde crea el acceso a la BBDD
    public CapituloDao(Context context)
    {
        db= new BBDDSQLiteHelper(context).getWritableDatabase();
    }

    //Devuelve todos los Capitulos de la BBDD
    public ArrayList<Capitulo> findAll()
    {
        ArrayList<Capitulo> capitulos = new ArrayList<>();

        Cursor c=db.rawQuery("select nombre, rutaFic, hecho, padre, siguiente from Capitulo order by nombre", null);
        if(c.moveToFirst())
        {
            do {
                String nombre = c.getString(0);
                 String rutaFic = c.getString(1);
                Integer h = c.getInt(2);
                Boolean hecho = Boolean01.gestionInt(h);
                String padre = c.getString(3);
                String siguiente = c.getString(4);

                Capitulo capitulo = new Capitulo(nombre, rutaFic, hecho, siguiente, padre);

                LinkedList<Capitulo> hijos = (LinkedList<Capitulo>) findHijos(capitulo);
                if(hijos!=null)
                    capitulo.setHijos(hijos);

                capitulos.add(capitulo);

            }while(c.moveToNext());
        }

        if(capitulos.size()<=0)
            capitulos=null;

        return capitulos;
    }

    //Devuelve el capitulo que tenga el mismo nombre que el Capitulo que se le pasa como parametro
    //Devuelve null si no lo encuentra
    public Capitulo find(Capitulo cap)
    {
        Capitulo capitulo=null;
        Cursor c=db.rawQuery("select nombre, rutaFic, hecho, padre, siguiente from Capitulo where nombre=?", new String[]{cap.getNombre()});
        if(c.moveToFirst())
        {
                String nombre=c.getString(0);
                String rutaFic=c.getString(1);
                Integer h=c.getInt(2);
                Boolean hecho= Boolean01.gestionInt(h);  String padre = c.getString(3);
                String siguiente = c.getString(4);

                capitulo = new Capitulo(nombre, rutaFic, hecho, siguiente, padre);
        }

        return capitulo;
    }

    public List<Capitulo> findHijos(Capitulo cap)
    {
        LinkedList<Capitulo> hijos = new LinkedList<>();

        Cursor c=db.rawQuery("select nombre, rutaFic, hecho,  padre, siguiente from Capitulo where padre = '" + cap.getNombre() + "' order by nombre", null);
        if(c.moveToFirst())
        {
            do {
                String nombre=c.getString(0);
                String rutaFic=c.getString(1);
                Integer h=c.getInt(2);
                Boolean hecho= Boolean01.gestionInt(h);
                String padre = c.getString(3);
                String siguiente = c.getString(4);

                Capitulo capitulo = new Capitulo(nombre, rutaFic, hecho, siguiente, padre);
                hijos.add(capitulo);

            } while(c.moveToNext());
        }

        return hijos;
    }

}
