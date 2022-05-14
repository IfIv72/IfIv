package com.example.if_iv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.Interfaz.CapitulosMain;
import com.example.if_iv.Interfaz.JuegoMain;
import com.example.if_iv.Interfaz.MinijuegosMain;
import com.example.if_iv.Interfaz.PersonajesMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
    }

    public void irMinijuegos(View v)
    {
        Intent intent = new Intent(MainActivity.this, MinijuegosMain.class);
        startActivity(intent);
    }

    public void irCapitulos(View v)
    {
        Intent intent = new Intent(MainActivity.this, CapitulosMain.class);
        startActivity(intent);
    }

    public void irPersonajes(View v)
    {
        Intent intent = new Intent(MainActivity.this, PersonajesMain.class);
        startActivity(intent);
    }

    public void jugar(View v)
    {
        Intent intent = new Intent(MainActivity.this, JuegoMain.class);
        startActivity(intent);
    }

    public void abrirPrueba(View v)
    {
        Intent intent = new Intent(MainActivity.this, prubasFechas.class);
        startActivity(intent);
    }
}