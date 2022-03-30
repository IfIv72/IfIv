package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.Ayudas.Boolean01;
import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.Capitulo;
import com.example.if_iv.model.Dios;

import java.util.ArrayList;

//findall
//find
public class DiosDao
{

    private SQLiteDatabase db;

    public DiosDao(Context context)
    {
        db= new BBDDSQLiteHelper(context).getWritableDatabase();
    }

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

    public Dios find(Dios d)
    {
        Dios dios=null;
        Cursor c=db.rawQuery("select nombre, rutaFic, hecho from Capitulo where nombre=?", new String[]{dios.getNombre()});
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

}
