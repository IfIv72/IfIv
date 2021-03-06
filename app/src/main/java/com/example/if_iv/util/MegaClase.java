package com.example.if_iv.util;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.if_iv.R;
import com.example.if_iv.dao.DiosDao;
import com.example.if_iv.model.Dios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// tiene metodos staticos que ayudaran al resto de clases para ahorrar lineas de codigo
public class MegaClase extends AppCompatActivity {
    public static final int[] diosesPremio = {
            R.drawable.isis_feliz,
            R.drawable.apolo_feliz,
            R.drawable.anubis_feliz,
            R.drawable.dionisio_feliz};

    /////  IMAGENES
    public static int imgSegunDios(String dios, String estado) {// descomentar al añadir las imagenes
        switch (dios) {
            case "Loki":
                if (estado.equals("feliz"))
                    return R.drawable.loki_feliz;
                if (estado.equals("triste"))
                    return R.drawable.loki_triste;
                if (estado.equals("enfadado"))
                    return R.drawable.loki_enfadado;
                if (estado.equals("normal"))
                    return R.drawable.loki_normal;
            case "Freya":
                if (estado.equals("feliz"))
                    return R.drawable.freya_feliz;
                if (estado.equals("triste"))
                    return R.drawable.freya_triste;
                if (estado.equals("enfadado"))
                    return R.drawable.freya_enfadado;
                if (estado.equals("normal"))
                    return R.drawable.freya_normal;
            case "Anubis":
                if (estado.equals("feliz"))
                    return R.drawable.anubis_feliz;
                if (estado.equals("triste"))
                    return R.drawable.anubis_triste;
                if (estado.equals("enfadado"))
                    return R.drawable.anubis_enfadado;
                if (estado.equals("normal"))
                    return R.drawable.anubis_normal;
            case "Isis":
                if (estado.equals("feliz"))
                    return R.drawable.isis_feliz;
                if (estado.equals("triste"))
                    return R.drawable.isis_triste;
                if (estado.equals("enfadado"))
                    return R.drawable.isis_enfadado;
                if (estado.equals("normal"))
                    return R.drawable.isis_normal;
            case "Apolo":
                if (estado.equals("feliz"))
                    return R.drawable.apolo_feliz;
                if (estado.equals("triste"))
                    return R.drawable.apolo_triste;
                if (estado.equals("enfadado"))
                    return R.drawable.apolo_enfadado;
                if (estado.equals("normal"))
                    return R.drawable.apolo_normal;
            case "Hermes":
                if (estado.equals("feliz"))
                    return R.drawable.hermes_feliz;
                if (estado.equals("triste"))
                    return R.drawable.hermes_triste;
                if (estado.equals("enfadado"))
                    return R.drawable.hermes_enfadado;
                if (estado.equals("normal"))
                    return R.drawable.hermes_normal;
            case "Dionisio":
                if (estado.equals("feliz"))
                    return R.drawable.dionisio_feliz;
                if (estado.equals("triste"))
                    return R.drawable.dionisio_triste;
                if (estado.equals("enfadado"))
                    return R.drawable.dionisio_enfadado;
                if (estado.equals("normal"))
                    return R.drawable.dionisio_normal;
//            case "iv":
//                if(estado.equals("chibi"))
//                    return R.drawable.iv_chibi;
//                if(estado.equals("feliz"))
//                    return R.drawable.iv_feliz;
//                if(estado.equals("triste"))
//                    return R.drawable.iv_triste;
//                if(estado.equals("enfadado"))
//                    return R.drawable.iv_enfadado;
//                if(estado.equals("normal"))
//                    return R.drawable.iv;
            default:
                return R.drawable.hoguera;
        }
    }

    ///// COLORES
    public int colorSegun(String dios, Context context) {
        switch (dios) {
            case "griega":
                return ContextCompat.getColor(context, R.color.griega);
            case "nordica":
                return ContextCompat.getColor(context, R.color.nordica);
            case "egipcia":
                return ContextCompat.getColor(context, R.color.egipcia);
            case "eleccion":
                return ContextCompat.getColor(context, R.color.eleccion);
            case "Loki":
                return ContextCompat.getColor(context, R.color.loki);
            case "Freya":
                return ContextCompat.getColor(context, R.color.freya);
            case "Anubis":
                return ContextCompat.getColor(context, R.color.anubis);
            case "Isis":
                return ContextCompat.getColor(context, R.color.isis);
            case "Apolo":
                return ContextCompat.getColor(context, R.color.apolo);
            case "Hermes":
                return ContextCompat.getColor(context, R.color.hermes);
            case "Dionisio":
                return ContextCompat.getColor(context, R.color.dionisio);
            case "iv":
                return ContextCompat.getColor(context, R.color.iv);
            default:
                return ContextCompat.getColor(context, R.color.black);
        }
    }

    ///// CONVERSORES (sqlite)

    //Booleans
//Esto nos va a dar problemas a la hora de hacer las pruebas por que siempre da un valor valido jeje
    public static boolean gestionInt(Integer inte)
    {
        return inte != 0;
    }

    public static Integer gestionBoolean(Boolean boo)
    {
        return boo ? 1 : 0;
    }

    //Fechas
    //yyyy-mm-dd hh:mm:ss

    public static String deFechaAString(Date date)
    {
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str= format.format(date);
        return str;
    }

    public static Date deStringAFecha(String str) {

        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(str);
            return date;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }

    }


}
