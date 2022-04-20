package com.example.if_iv.Interfaz;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.if_iv.Minijuegos.CartasMinijuego;
import com.example.if_iv.Minijuegos.CasinoMinijuego;
import com.example.if_iv.Minijuegos.CofresMinijuego;
import com.example.if_iv.Minijuegos.PreguntassMinijuego;
import com.example.if_iv.R;
import com.example.if_iv.util.Megaclase;

public class MinijuegosMain extends AppCompatActivity {

    // se muestra si se ha llegado al tope de puntos
    private DialogoAviso dialogoAviso;
    private FragmentManager fragmentManager;

    private ImageButton btnCofre, btnPreguntas,btnCasino, btnCartas;
    private TextView titCofre, titPregunta,titCasino,titCartas, lblPuntos;
    private RelativeLayout bloqCasino,bloqCartas;  //aparecen cuando el minijuego esta bloqueado
    private int TOPE = 200;
    private int puntosGanados=52;

    // borrar una vez se hagan consultando la bbdd
    private boolean bloqueado = true;
    private boolean preguntasJugado = false;  // guardado como variable o cambiara segun el ultimo dia completado o asi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minijuegos_main);

        getSupportActionBar().hide();


        lblPuntos = findViewById(R.id.lblPuntos);

        btnCofre = findViewById(R.id.btnMCofres);
        btnPreguntas = findViewById(R.id.btnMPreguntas);
        btnCasino = findViewById(R.id.btnMCasino);
        btnCartas = findViewById(R.id.btnMCartas);

        titCofre = findViewById(R.id.titCofre);
        titPregunta = findViewById(R.id.titPreguntas);
        titCasino = findViewById(R.id.titCasino);
        titCartas = findViewById(R.id.titCartas);

        bloqCasino = findViewById(R.id.layBloqCasino);
        bloqCartas = findViewById(R.id.layBloqCartas);

        dibujar();
        gestionarBloqueos();
        gestionarEventos();
    }

    // da formato a los shape, puntos
    public void dibujar()
    {
        //actualiza los puntos
        lblPuntos.setText(puntosGanados+" puntos de "+TOPE);
        GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_dialogo);
        lblPuntos.setBackground(draw);


    }

    // aviso si los puntos superan o llegan al tope
    public void comprobarPuntos()
    {
        if(puntosGanados >= TOPE)
        {
            String texto = getString(R.string.tope_alcanzado);
            int img = Megaclase.imgSegunDios("Loki","chibi");
            dialogoAviso = new DialogoAviso(texto,"Tope alcanzado", img);
            dialogoAviso.show(getSupportFragmentManager(), "dialogo_tope_alcanzado");
        }
    }

    // Comprueba si el minijuego ha sido desbloqueado.
    // Si esta desbloqueado quita el layout que bloquea y habilita el boton
    public void gestionarBloqueos()
    {
        //consulta sobre el proceso de la historia
        String sql;
        if(bloqueado)
        {
            bloqCartas.setVisibility(View.GONE);
        }
    }


    public void gestionarEventos()
    {
        // click lblPuntos
        lblPuntos.setOnClickListener(view -> {
            // se abre dialogo explicando las condiciones
            String texto = getString(R.string.codicion_tope_puntos);
            int img = Megaclase.imgSegunDios("Isis","chibi");
            dialogoAviso = new DialogoAviso(texto,"Control de puntos", img);
            dialogoAviso.show(getSupportFragmentManager(), "dialogo_control_puntos");
        });


        // avisos de minijuegos bloqueados
        bloqCartas.setOnClickListener(view -> {
            Toast.makeText(MinijuegosMain.this,"Iv queria ir a la piscina...",Toast.LENGTH_LONG).show();
        });
        bloqCasino.setOnClickListener(view -> {
            Toast.makeText(MinijuegosMain.this,"Dia loco en el casino!!!",Toast.LENGTH_LONG).show();
        });


        //minijuegos desbloqueados
        btnCofre.setOnClickListener(view -> {
            comprobarPuntos();
            Intent intento = new Intent(MinijuegosMain.this, CofresMinijuego.class);
            startActivity(intento);
        });

        btnPreguntas.setOnClickListener(view -> {
            if(preguntasJugado)
                Toast.makeText(MinijuegosMain.this,"Solo UNA vez por dia",Toast.LENGTH_LONG).show();
            else
            {
                comprobarPuntos();
                Intent intento = new Intent(MinijuegosMain.this, PreguntassMinijuego.class);
                startActivity(intento);
                preguntasJugado = true;
            }
        });
        btnCasino.setOnClickListener(view -> {
            comprobarPuntos();
            Intent intento = new Intent(MinijuegosMain.this, CasinoMinijuego.class);
            startActivity(intento);
        });
        btnCartas.setOnClickListener(view -> {
            comprobarPuntos();
            Intent intento = new Intent(MinijuegosMain.this, CartasMinijuego.class);
            startActivity(intento);
        });
    }

}
