package com.example.if_iv.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaJugador {

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
