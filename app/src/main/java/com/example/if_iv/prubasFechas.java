package com.example.if_iv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.if_iv.Ayudas.FechaJugador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class prubasFechas extends AppCompatActivity {

    ////yyyy-mm-dd hh:mm:ss

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prubas_fechas);

        try {
            x();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void x() throws ParseException
    {
        TextView txt=findViewById(R.id.txtFecha);

        String str= FechaJugador.deFechaAString(new Date());
        Date date=FechaJugador.deStringAFecha("2021-11-07 12:41:01");
        String s=FechaJugador.deFechaAString(date);

        txt.setText(str+"\n"+date.toString()+"\n"+s);
    }
}