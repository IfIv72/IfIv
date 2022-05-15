package com.example.if_iv.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;

public class EleccionesDao {

    private SQLiteDatabase db;

    public EleccionesDao(Context context)
    {
        db= new BBDDSQLiteHelper(context).getWritableDatabase();
    }

    public void hacerUpdate(String query)
    {
        db.execSQL(query);
    }
}
