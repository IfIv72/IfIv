package com.example.if_iv.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BBDDSQLiteHelper extends SQLiteOpenHelper
{
    private String createJugador="CREATE TABLE 'Jugador' ("+
                                    	"'nombre'	TEXT,"+
                                    	"'capitulo'	TEXT,"+
                                    	"'comienzoCap'	TEXT,"+
                                    	"'puntos'	INTEGER"+
                                    ");";

    private String createDios="CREATE TABLE 'Dios' (" +
            "'nombre' TEXT," +
            "'afinifad' TEXT," +
            "'info' TEXT," +
            "'rutaImg' TEXT," +
            "'mitologia' TEXT" +
            ");";

    private String createCapitulo="CREATE TABLE 'Capitulo' (" +
            "'nombre' TEXT" +
            "'rutaFic' TEXT" +
            "'hecho' INTEGER," +
            ");";

    private String createMegaEleccion="CREATE TABLE 'MegaEleccion' (" +
            "'decision' TEXT," +
            "'hecha' INTEGER," +
            "'rutaFic' TEXT" +
            ");";

    private ArrayList<String> creates;

    public BBDDSQLiteHelper(@Nullable Context context) {
        super(context, "BBDD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        creates.add(createCapitulo);
        creates.add(createDios);
        creates.add(createJugador);
        creates.add(createMegaEleccion);

        for (String create: creates)
        {
            db.execSQL(create);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        for (String create: creates)
        {
            db.execSQL(create);
        }

    }
}
