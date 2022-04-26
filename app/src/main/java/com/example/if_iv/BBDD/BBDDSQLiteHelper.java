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

    private Context context;

    //Constructor, solo necesitas contexto lo demas no cambiara
    public BBDDSQLiteHelper(@Nullable Context context) {

        super(context, "BBDD", null, 1);
        this.context=context;
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
        crearBBDD(db);
    }

    //Si ha cambios en la BBDD
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

    //Metodo en el que se crean las tablas y les inserta los datos
    public void crearBBDD(SQLiteDatabase db)
    {
        for (String create: creates)
        {
            db.execSQL(create);
        }
        insertData(db);
    }

    //Metodo en el que se insertan los datos que esten en ela rchivo assets/data.sql
    //Ese archivo tiene que estar en sql y no tener comentarios
    public void insertData(SQLiteDatabase db)
    {
        try
        {
            //Lee el fichero
            BufferedReader br= new BufferedReader(new InputStreamReader(this.context.getAssets().open("data.sql")));
            //Prepara la bbdd
            db.beginTransaction();
            String line= br.readLine();
            while(line!=null)
            {
                if(line.length()>0)
                {
                    //ejecuta la linea leida en el fichero en la bbdd
                    db.execSQL(line);
                }
                line=br.readLine();
            }
            //cierra la bbdd
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        catch (IOException e)
        {
            Log.i("errrorBD",e.getMessage());
        }

    }
}
