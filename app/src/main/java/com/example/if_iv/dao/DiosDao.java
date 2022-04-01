package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.Dios;

import java.util.ArrayList;

//findall
//find
public class DiosDao
{

    //BBDD
    private SQLiteDatabase db;

    //Constructor donde crea el acceso a la BBDD
    public DiosDao(Context context)
    {
        db= new BBDDSQLiteHelper(context).getWritableDatabase();
    }

    //Devuelve todos los Dioses de la BBDD
    public ArrayList<Dios> findAll()
    {
        ArrayList<Dios> dioses = new ArrayList<Dios>();

        Cursor c=db.rawQuery("select nombre, afinidad, info, rutaImg, mitologia from Dios order by nombre", null);
        if(c.moveToFirst())
        {
            do {
                String nombre=c.getString(0);
                int afinidad=c.getInt(1);
                String info=c.getString(2);
                String rutaImg=c.getString(3);
                String mitologia=c.getString(1);

                Dios dios=new Dios(nombre,afinidad,info,rutaImg,mitologia);

                dioses.add(dios);

            }while(c.moveToNext());
        }

        if(dioses.size()<=0)
            dioses=null;
        return dioses;
    }

    //Devuelve el Dios que tenga el mismo nombre que el Dios que se le pasa como parametro
    //Devuelve null si no lo encuentra
    public Dios find(Dios d)
    {
        Dios dios=null;
        Cursor c=db.rawQuery("select nombre, afinidad, info, rutaImg, mitologia from Dios where nombre=?", new String[]{d.getNombre()});
        if(c.moveToFirst())
        {
            String nombre=c.getString(0);
            int afinidad=c.getInt(1);
            String info=c.getString(2);
            String rutaImg=c.getString(3);
            String mitologia=c.getString(1);

            dios= new Dios(nombre,afinidad,info,rutaImg,mitologia);

        }

        return dios;
    }

    //Se le establecera al Dios que tenga el mismo nombre que el que se le pasa como parametro la afinidad de este
    public void updateAfinidad(Dios d)
    {
        String query="update Dios set afinidad="+d.getAfinidad()+" where nombre='"+d.getNombre()+"'";
        db.execSQL(query);
    }

}
