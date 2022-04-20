package com.example.if_iv.Interfaz;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.if_iv.R;

public class JuegoMain extends AppCompatActivity {

    private TextView lblDialogo, lblNombre;
    private ImageView imgDios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_main);

        getSupportActionBar().hide();

        lblNombre = findViewById(R.id.lblNombre);
        lblDialogo = findViewById(R.id.lblDialogo);
        imgDios = findViewById(R.id.imgDios);

        leer();
    }

    // va leyando el fichero de la historia
    public void leer()
    {

    }

}
