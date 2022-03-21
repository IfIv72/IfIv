package com.example.if_iv.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDDSQLiteHelper extends SQLiteOpenHelper
{
    private String createJugador="CREATE TABLE 'Jugador' ("+
                                    	"'nombre'	TEXT,"+
                                    	"'capitulo'	TEXT,"+
                                    	"'comienzoCap'	TEXT,"+
                                    	"'puntos'	INTEGER"+
                                    ");";

    private String createCapitulo=;

    public BBDDSQLiteHelper(@Nullable Context context) {
        super(context, "BBDD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
