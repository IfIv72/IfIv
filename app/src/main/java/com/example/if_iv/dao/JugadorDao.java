package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.Jugador;
import com.example.if_iv.model.MegaEleccion;
import com.example.if_iv.util.MegaClase;

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
        Cursor c=db.rawQuery("select nombre, capitulo, comienzoCap, puntos, preguntas from Usuario", null);

        if(c.moveToFirst())
        {
            String nombre=c.getString(0);
            String capitulo=c.getString(1);

            String cc=c.getString(2);
            Date comienzoCap= MegaClase.deStringAFecha(cc);

            int puntos=c.getInt(3);

            String p=c.getString(4);
            Date preguntas = MegaClase.deStringAFecha(p);

            jugador= new Jugador(nombre,capitulo,comienzoCap,puntos, preguntas);
        }

        return jugador;
    }

    public boolean jugadoHoyPreguntas()
    {
        String query="select preguntas from Jugador";
        Cursor c= db.rawQuery(query, null);
        if(c.moveToFirst())
        {
            String preguntas=c.getString(0);
            Date ultima= MegaClase.deStringAFecha(preguntas);
            Date hoy= new Date();

            if(ultima.getYear()==hoy.getYear() && ultima.getMonth()==hoy.getMonth() && ultima.getDay()==hoy.getDay())
            {
                return true;
            }

        }

        return false;
    }

    public void updatePreguntas(Date preguntas)
    {
        String query="update Jugador set preguntas="+MegaClase.deFechaAString(preguntas);
        db.execSQL(query);
    }

    public void updatePreguntas(String preguntas)
    {
        String query="update Jugador set preguntas="+preguntas;
        db.execSQL(query);
    }

    public void updateNombre()
    {

    }

    public void updatePuntos()
    {

    }

    public void updateCapitulo()
    {

    }

    public void updateComienzoCap()
    {

    }

}
