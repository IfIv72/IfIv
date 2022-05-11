package com.example.if_iv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.model.MegaEleccion;
import com.example.if_iv.util.MegaClase;

//find
//update
public class MegaEleccionDao
{

    private SQLiteDatabase db;

    public MegaEleccionDao(Context context)
    {
        db= new BBDDSQLiteHelper(context).getWritableDatabase();
    }

    public MegaEleccion find(MegaEleccion me)
    {
        MegaEleccion megaEleccion=null;
        Cursor c=db.rawQuery("select decision, hecha, rutaFic from MegaEleccion where decision=?", new String[]{megaEleccion.getDecision()});
        if(c.moveToFirst())
        {
            String decision=c.getString(0);
            int hecha=c.getInt(1);
            String rutaFic=c.getString(2);

            megaEleccion= new MegaEleccion(decision,hecha,rutaFic);

        }

        return megaEleccion;
    }

    public void updateHecha(MegaEleccion me)
    {
        int hecha= MegaClase.gestionBoolean(me.isHecha());
        String query="update MegaEleccion set hecha="+hecha;
        db.execSQL(query);
    }

}
