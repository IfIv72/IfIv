package com.example.if_iv.Minijuegos;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.if_iv.R;
import com.example.if_iv.util.Megaclase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CofresMinijuego extends AppCompatActivity
{

    private GridLayout glCofres;
    private TextView txtIntentos;


    //Botones y si son los que tienen premio
    private HashMap<ImageButton, Integer> btsPremio;

    //Intentos realizables
    private int intentos;

    //Intentos hechos
    private int iHechos;

    //Mensaje sobre los intentos restantes
    private String msj;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cofres_minijuegos);
        getSupportActionBar().hide();

        btsPremio= new HashMap<ImageButton, Integer>();
        iHechos=0;
        msj=" intentos restantes";

        glCofres =findViewById(R.id.glCofres);
        txtIntentos= findViewById(R.id.txtIntentos);

        cargarLayout();
        cargarPremio();
        cargarEventos();
    }

    //Introduce en el grid layout botones
    private void cargarLayout()
    {

        //Numero indefinido de botones de 2 a 8
        int n= (int) (1+Math.random()*7);
//        int n=7;
        Log.i("cofres",n+"");
        cargarIntentos(n);

        int w=500;
        int h=500;

        //Si solo son dos botones seran mas grandesy estaran alineados verticalmente
        if(n==1)
        {
            glCofres.setColumnCount(1);
            w=900;
            h=900;
        }

        for (int i =0; i<=n;i++)
        {
            //Crea el boton con sus atributos
            ImageButton imgbt= new ImageButton(this.getBaseContext());

            //Imagen y fondo
            imgbt.setImageResource(R.drawable.puerta);
            imgbt.setBackgroundColor(getResources().getColor(R.color.nada));
            imgbt.setScaleType(ImageView.ScaleType.FIT_CENTER);

            //ID
            imgbt.setId(i);

            //Margenes
            GridLayout.LayoutParams lp= new GridLayout.LayoutParams();
            lp.setGravity(Gravity.CENTER);
            lp.setMargins(5,5,5,0);

            //Dimendiones
            lp.width=w;
            lp.height=h;

            //Añadir Margenes y Dimensiones
            imgbt.setLayoutParams(lp);

            //Añadir boton a layout
            glCofres.addView(imgbt);

            //Añadir a lista sin premio
            btsPremio.put(imgbt,0);

        }
    }

    private void cargarPremio()
    {

        //Escoge un boton
        int n= (int) (0+Math.random()*(btsPremio.size()-2));
//        int n=4;

        //Recoge el boton
        ImageButton imgbt= (ImageButton) btsPremio.keySet().toArray()[n];

        //Cambia el value en el has map del boton
        btsPremio.replace(imgbt,1);
        Log.i("premio",imgbt.getId()+"");
    }

    private void cargarEventos()
    {

        //Recorre HashMap
        Set imgbts=btsPremio.keySet();

        Iterator<ImageButton> iterator=imgbts.iterator();
        while (iterator.hasNext())
        {
            ImageButton imgbt= iterator.next();

            //Si no tiene premio
            if(btsPremio.get(imgbt)==0)
            {
                imgbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarIntentos();
                        if(iHechos>=intentos)
                        {

                            //Muestra mensaje
                            Toast.makeText(getBaseContext(),"PERDISTE",Toast.LENGTH_SHORT).show();
                            borrarPuertas(Techniques.Shake,2);
                            //Cierra el minijuego al medio segundo
                            Handler handler= new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 500);
                        }
                    }
                });
            }
            //Si tiene premio
            else
            {
                imgbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarIntentos();
                        cambiarImg(imgbt);
                        Toast.makeText(getBaseContext(),"GANASTE",Toast.LENGTH_SHORT).show();
                        borrarPuertas(Techniques.FadeOutDown,0);
                        Handler handler= new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1200);
                    }
                });
            }
        }
    }

    //Dependiendo del numero de cofres que aparece en pantalla se tendran mas intentos
    private void cargarIntentos(int n)
    {
        if(n<=3)
        {
            intentos=1;
        }
        else
        {
            if(n<=5)
            {
                intentos=2;
            }
            else
            {
                intentos=3;
            }
        }

        txtIntentos.setText(intentos+msj);
    }

    private void actualizarIntentos()
    {
        iHechos++;
        txtIntentos.setText((intentos-iHechos)+msj);
    }

    private void cambiarImg(ImageButton imgbt)
    {
        int[] diosesPremio=Megaclase.diosesPremio;
        int i= (int) (Math.random()* diosesPremio.length);
        imgbt.setImageResource(diosesPremio[i]);
        GridLayout.LayoutParams lp= new GridLayout.LayoutParams();
        lp.width=imgbt.getWidth()+100;
        lp.height=imgbt.getHeight()+100;
        imgbt.setLayoutParams(lp);

        YoYo.with(Techniques.ZoomIn)
                .duration(600)
                .repeat(0)
                .playOn(imgbt);
        YoYo.with(Techniques.Swing)
                .duration(500)
                .repeat(1)
                .playOn(imgbt);
    }

    private void borrarPuertas(Techniques technique, int repeat)
    {
        Set imgbts=btsPremio.keySet();

        Iterator<ImageButton> iterator=imgbts.iterator();
        while (iterator.hasNext()) {
            ImageButton imgbt = iterator.next();

            if (btsPremio.get(imgbt) == 0) {
                YoYo.with(technique)
                        .duration(500)
                        .repeat(repeat)
                        .playOn(imgbt);
            }

        }
    }

}













