package com.example.if_iv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irMinijuegos(View v)
    {
        Intent intent = new Intent(MainActivity.this, prubasFechas.class);
        startActivity(intent);
    }

    public void irCapitulos(View v)
    {
        Intent intent = new Intent(MainActivity.this, prubasFechas.class);
        startActivity(intent);
    }

    public void irPersonajes(View v)
    {
        Intent intent = new Intent(MainActivity.this, prubasFechas.class);
        startActivity(intent);
    }

    public void jugar(View v)
    {
        Intent intent = new Intent(MainActivity.this, prubasFechas.class);
        startActivity(intent);
    }

    public void abrirPrueba(View v)
    {
        Intent intent = new Intent(MainActivity.this, prubasFechas.class);
        startActivity(intent);
    }
}