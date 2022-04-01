package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.util.FechaJugador;
import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.Jugador;

import java.util.Date;

//find
//update
public class JugadorDao
{

    //BBDD
    private SQLiteDatabase db;

    //Constructor donde crea el acceso a la BBDD
    public JugadorDao(Context context)
    {
        db= new BBDDSQLiteHelper(context).getWritableDatabase();
    }

    //Devuelve al jugador
    //Devuelve null si no lo encuentra
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

    //Cambia todos los artibutos del jugador que no sean null (o -1 en puntos) en el parametro por los de este
    public void update(Jugador j)
    {
        String query=queryUpdate(j);
        if(query!=null)
            db.execSQL(query);

    }

    //Crea la query update dependiendo de los campos null
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
