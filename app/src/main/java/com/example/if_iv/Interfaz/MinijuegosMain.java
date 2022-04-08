package com.example.if_iv.Interfaz;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
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
    private boolean preguntasJugado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minijuegos_main);

        lblPuntos = findViewById(R.id.lblPuntos);

        btnCofre = findViewById(R.id.btnMCofres);
        btnPreguntas = findViewById(R.id.btnMPreguntas);
        btnCasino = findViewById(R.id.btnMCasino);
        btnCartas = findViewById(R.id.btnMCartas);
        btnCofre.setEnabled(false);     // se habilita mas adelante
        btnCartas.setEnabled(false);    // se habilita mas adelante

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
        GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_bordes_redondos);
        draw.setColor(ContextCompat.getColor(this,R.color.capitulo));
        draw.setCornerRadius(15);
        lblPuntos.setBackground(draw);


    }

    // aviso si los puntos superan o llegan al tope
    public void comprobarPuntos()
    {
        if(puntosGanados >= TOPE)
        {
            /*
            fragmentManager = getSupportFragmentManager();
            dialogoAviso = new DialogoAviso();
            dialogoAviso.setLblTexto("Has alcanzado el tope de puntos. Ya no ganaras " +
                    "más puntos pero puedes seguir jugando");
            dialogoAviso.show(fragmentManager,"Dialogo");
             */
            Toast.makeText(MinijuegosMain.this,"tope alcanzado",Toast.LENGTH_SHORT).show();
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
            btnCartas.setEnabled(true);
        }
    }


    public void gestionarEventos()
    {
        // click lblPuntos
        lblPuntos.setOnClickListener(view -> {
            // se abre dialogo explicando las condiciones
            Toast.makeText(MinijuegosMain.this,"explicacion tope",Toast.LENGTH_SHORT).show();
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
