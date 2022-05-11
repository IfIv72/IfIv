package com.example.if_iv.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BBDDSQLiteHelper extends SQLiteOpenHelper
{

    private Context context;

    /**
     *   Constructor, solo necesitas contexto lo demas no cambiara
     */
    public BBDDSQLiteHelper(@Nullable Context context) {

        super(context, "BBDD", null, 1);
        this.context=context;
    }

    /**
     *   Creacion de la base de datos
     *   Path: data/data/com.example.if_iv/databases/BBDD
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        crearBBDD(db);
    }

    /**
     *  Si ha cambios en la BBDD
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se borran todas las tablas
        db.execSQL("drop table if exists Dios");
        db.execSQL("drop table if exists Capitulo");
        db.execSQL("drop table if exists Jugador");
        db.execSQL("drop table if exists MegaEleccion");

        //Se vuelven a crear las tablas
        crearBBDD(db);

    }

    /**
     *   Metodo en el que se crean las tablas y les inserta los datos
     */
    public void crearBBDD(SQLiteDatabase db)
    {

        Stream<String> sql = null;
        try {
            sql = new BufferedReader(
                new InputStreamReader(
                        this.context.getAssets().open("tables.sql"),
                        StandardCharsets.UTF_8
                )
            ).lines().filter(l -> l.length() != 0);

            sql.forEach(create -> {
                db.execSQL(create);
                Log.i("BBDD", "Tabla creada: " + create);
            } );
            insertData(db);
        } catch (IOException e) {
            Log.i("Error BD","La base de datos no se pudo crear.");
        }


    }

    /**
     * Metodo en el que se insertan los datos que esten en la archivo assets/data.sql
     * El archivo tiene que estar en sql y no tener comentarios
     * @param db La conexión a la base de datos
     */
    public void insertData(SQLiteDatabase db)
    {
        try
        {
            //Lee el fichero y filtra las lineas en blanco
            Stream<String> lines = new BufferedReader(
                    new InputStreamReader(this.context.getAssets().open("data.sql"))
            ).lines().filter(l-> l.length() != 0);

            //Prepara la bbdd
            db.beginTransaction();
            lines.forEach(line -> {
                db.execSQL(line);
                Log.i("BBDD", "Insert completada: " + line);
            });

            //cierra la bbdd
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        catch (IOException e)
        {
            Log.i("errorBD",e.getMessage());
        }

    }
}
