package com.example.if_iv.Interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.if_iv.R;
import com.example.if_iv.dao.JugadorDao;

public class AjustesMain extends AppCompatActivity {

    private JugadorDao jugador;
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
        if(modoOscuro.isChecked())
        {
            Toast.makeText(this, "on", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "off", Toast.LENGTH_SHORT).show();
        }

        //Puntos
        layPuntos.setOnClickListener(view -> {
            //dialogo confirmacion

        });

        //Cambiar nombre
        btnCambioNom.setOnClickListener(view -> {
            // conseguir nombre
            Toast.makeText(this,"Sin Implementar", Toast.LENGTH_SHORT).show();
        });

        //Creditos
        btnCreditos.setOnClickListener(view -> {
            Intent intento = new Intent(AjustesMain.this , CreditosMain.class);
            startActivity(intento);
        });

        //Tutorial
        btnTutorial.setOnClickListener(view -> {
            Toast.makeText(this,"Sin Implementar", Toast.LENGTH_SHORT).show();
        });

        //Reiniciar
        btnReinicio.setOnClickListener(view -> {
            Toast.makeText(this,"Sin Implementar", Toast.LENGTH_SHORT).show();
        });

    }
}
