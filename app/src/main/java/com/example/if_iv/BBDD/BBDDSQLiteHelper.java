package com.example.if_iv.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BBDDSQLiteHelper extends SQLiteOpenHelper
{

    //Strings para la creacion de las tablas de la BBDD
    private final String createJugador="CREATE TABLE 'Jugador' ("+
                                    	"'nombre'	TEXT,"+
                                    	"'capitulo'	TEXT,"+
                                    	"'comienzoCap'	TEXT,"+
                                    	"'puntos'	INTEGER"+
                                    ");";

    private final String createDios="CREATE TABLE 'Dios' (" +
            "'nombre' TEXT," +
            "'afinidad' TEXT," +
            "'info' TEXT," +
            "'rutaImg' TEXT," +
            "'mitologia' TEXT" +
            ");";

    private final String createCapitulo="CREATE TABLE 'Capitulo' (" +
            "'nombre' TEXT," +
            "'rutaFic' TEXT," +
            "'hecho' INTEGER" +
            ");";

    private final String createMegaEleccion="CREATE TABLE 'MegaEleccion' (" +
            "'decision' TEXT," +
            "'hecha' INTEGER," +
            "'rutaFic' TEXT" +
            ");";

    //ArrayList para guardar todos los SQLCrete
    private ArrayList<String> creates;

    //Constructor, solo necesitas contexto lo demas no cambiara
    public BBDDSQLiteHelper(@Nullable Context context) {

        super(context, "BBDD", null, 1);
        creates= new ArrayList<String>();
        creates.add(createCapitulo);
        creates.add(createDios);
        creates.add(createJugador);
        creates.add(createMegaEleccion);
    }

    //Creacion de la base de datos
    //Path: data/data/com.example.if_iv/databases/BBDD
    @Override
    public void onCreate(SQLiteDatabase db) {

        for (String create: creates)
        {
            db.execSQL(create);
        }
    }

    //Si ha cambios en la BBDD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se borran rodas las tablas
        db.execSQL("drop table if exists Dios");
        db.execSQL("drop table if exists Capitulo");
        db.execSQL("drop table if exists Jugador");
        db.execSQL("drop table if exists MegaEleccion");

        //Se vuelven a crear las tablas
        for (String create: creates)
        {
            db.execSQL(create);
        }

    }
}
