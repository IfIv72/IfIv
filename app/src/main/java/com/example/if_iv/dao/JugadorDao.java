package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.Ayudas.FechaJugador;
import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.Jugador;

import java.util.Date;

//find
//update
public class JugadorDao
{

    private SQLiteDatabase db;

    public JugadorDao(Context context)
    {
        db= new BBDDSQLiteHelper(context).getWritableDatabase();
    }

    public Jugador find()
    {

        Jugador jugador=null;
        Cursor c=db.rawQuery("select nombre, capitulo, comienzoCap, puntos from Usuario", null);

        if(c.moveToFirst())
        {
            String nombre=c.getString(0);
            String capitulo=c.getString(1);

            String cc=c.getString(2);
            Date comienzoCap= FechaJugador.deStringAFecha(cc);

            int puntos=c.getInt(3);

            jugador= new Jugador(nombre,capitulo,comienzoCap,puntos);
        }

        return jugador;
    }

    public void update(Jugador j)
    {
        String query=queryUpdate(j);
        if(query!=null)
            db.execSQL(query);

    }

    private String queryUpdate(Jugador j)
    {
        String query=null;

        String nombre=j.getNombre();
        String capitulo=j.getCapitulo();
        Date comienzoCap=j.getComienzoCap();
        int puntos=j.getPuntos();

        if(nombre!=null || capitulo!=null || comienzoCap!=null || puntos!=-1) {

            query = "update Jugador set ";

            if (nombre != null) {
                query = query + "nombre='" + nombre + "' ";
            }

            if (capitulo != null) {
                if (nombre != null)
                    query = query + ",capitulo='" + capitulo + "' ";
                else
                    query = query + "capitulo='" + capitulo + "' ";
            }

            if (comienzoCap != null) {
                String cc = FechaJugador.deFechaAString(comienzoCap);
                if (nombre != null || capitulo != nombre)
                    query = query + ",comienzoCap='" + cc + "' ";
                else
                    query = query + "comienzoCap='" + cc + "' ";
            }

            if (puntos != -1) {
                if (nombre != null || capitulo != nombre || comienzoCap != null)
                    query = query + ",puntos=" + puntos + " ";
                else
                    query = query + "puntos=" + puntos + " ";
            }

            query = query.substring(0, query.length() - 1) + ";";

        }

        return query;
    }

}
