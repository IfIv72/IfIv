package com.example.if_iv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.if_iv.Interfaz.DialogoNombre;
import com.example.if_iv.dao.DiosDao;
import com.example.if_iv.dao.JugadorDao;
import com.example.if_iv.model.Dios;
import com.example.if_iv.util.MegaClase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class prubasFechas extends AppCompatActivity implements DialogoNombre.DialogoNombreListener {

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
//
//        String str= MegaClase.deFechaAString(new Date());
//        Date date=MegaClase.deStringAFecha("2021-11-07 12:41:01");
//        String s=MegaClase.deFechaAString(date);
//
//        DiosDao dd= new DiosDao(this.getBaseContext());
//        ArrayList<Dios> dios=dd.findAll();
//        txt.setText(str+"\n"+date.toString()+"\n"+s+"\n"+dios.get(0));
//        Dios d= new Dios("Loki",-1,""," ","mito");
//        dd.updateAfinidad(d);
//        txt.setText(dd.find(d).toString());

        DialogoNombre dn= new DialogoNombre();
        FragmentManager fragmentManager= getSupportFragmentManager();
        dn.show(fragmentManager,"Dialogo nombre");


    }

    @Override
    public void onPossitiveButtonClick() {
        TextView txt=findViewById(R.id.txtFecha);
        JugadorDao jd= new JugadorDao(getBaseContext());
        txt.setText(jd.find().getNombre());
    }

    @Override
    public void onNegativeButtonClick() {

    }
}