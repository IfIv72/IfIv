package com.example.if_iv.Interfaz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.if_iv.R;

public class AjustesMain extends AppCompatActivity {

    private Switch modoOscuro;
    private Button btnReinicio, btnCambioNom, btnTutorial, btnCreditos;
    private RelativeLayout layPuntos;
    private TextView lblPuntos, lblLlaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes_main);

        getSupportActionBar().hide();

        //context = this.getBaseContext();

        layPuntos = findViewById(R.id.layContador);
        lblPuntos = findViewById(R.id.lblPuntos);
        lblLlaves = findViewById(R.id.lblLlaves);
        modoOscuro = findViewById(R.id.swDark);
        btnCambioNom = findViewById(R.id.btnSetNom);
        btnReinicio = findViewById(R.id.btnReinicio);
        btnTutorial = findViewById(R.id.btnTutorial);
        btnCreditos = findViewById(R.id.btnCreditos);

        colocarPuntos();
        gestionarEventos();
    }

    public void colocarPuntos()
    {
        lblPuntos.setText('d');
        lblLlaves.setText('d');
    }

    public void gestionarEventos()
    {

    }
}
