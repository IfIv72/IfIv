package com.example.if_iv.Interfaz;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.if_iv.Minijuegos.CartasMinijuego;
import com.example.if_iv.Minijuegos.CasinoMinijuego;
import com.example.if_iv.Minijuegos.CofresMinijuego;
import com.example.if_iv.Minijuegos.PreguntasMinijuego;
import com.example.if_iv.R;
import com.example.if_iv.dao.JugadorDao;
import com.example.if_iv.util.MegaClase;

import java.util.ArrayList;
import java.util.Date;

public class MinijuegosMain extends AppCompatActivity {

    // se muestra si se ha llegado al tope de puntos
    private DialogoAviso dialogoAviso;
    private FragmentManager fragmentManager;

    private ImageButton btnCofre, btnPreguntas,btnCasino, btnCartas;
    private ArrayList<ImageButton> btns;
    private TextView lblPuntos;
    private RelativeLayout bloqCasino,bloqCartas, bloqPreguntas;  //aparecen cuando el minijuego esta bloqueado
    private int TOPE = 200;
    private int puntosGanados=0;

    private boolean bloqueado = true;
    private boolean preguntasJugado;  // guardado como variable o cambiara segun el ultimo dia completado o asi

    private JugadorDao jugadorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minijuegos_main);

        getSupportActionBar().hide();

        jugadorDao= new JugadorDao(getBaseContext());

        lblPuntos = findViewById(R.id.lblPuntos);

        btns= new ArrayList<ImageButton>();
        btnCofre = findViewById(R.id.btnMCofres);
        btns.add(btnCofre);
        btnPreguntas = findViewById(R.id.btnMPreguntas);
        btns.add(btnPreguntas);
        btnCasino = findViewById(R.id.btnMCasino);
        btns.add(btnCasino);
        btnCartas = findViewById(R.id.btnMCartas);
        btns.add(btnCartas);

        bloqCasino = findViewById(R.id.layBloqCasino);
        bloqCartas = findViewById(R.id.layBloqCartas);
        bloqPreguntas = findViewById(R.id.layBloqPreguntas);

        dibujar();
        gestionarBloqueos();
        gestionarBotones();
        gestionarEventos();
    }

    // da formato a los shape, puntos
    public void dibujar()
    {
        //actualiza los puntos
        recargarPuntos();
        GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_dialogo);
        lblPuntos.setBackground(draw);


    }

    // Comprueba si el minijuego ha sido desbloqueado.
    // Si esta desbloqueado quita el layout que bloquea y habilita el boton
    public void gestionarBloqueos()
    {
        //consulta sobre si ha jugado hoy al minijuego de preguntas
        preguntasJugado=jugadorDao.jugadoHoyPreguntas();
        if(preguntasJugado)
        {
            Toast.makeText(this, "Ya has jugado al minijuego preguntas hoy", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(500).repeat(1).playOn(btnPreguntas);
            btnPreguntas.setEnabled(false);
            bloqPreguntas.setVisibility(View.VISIBLE);
        }
        else
        {
            bloqPreguntas.setVisibility(View.INVISIBLE);
        }
    }


    public void gestionarEventos()
    {
        // click lblPuntos
        lblPuntos.setOnClickListener(view -> {
            // se abre dialogo explicando las condiciones
            String texto = getString(R.string.codicion_tope_puntos);
            int img = MegaClase.imgSegunDios("Isis","normal");
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
            Intent intento = new Intent(MinijuegosMain.this, CofresMinijuego.class);
//            startActivity(intento);
            startActivityForResult(intento, 1277);
        });

        btnPreguntas.setOnClickListener(view -> {
            if(preguntasJugado)
                Toast.makeText(MinijuegosMain.this,"Solo UNA vez por dia",Toast.LENGTH_LONG).show();
            else
            {
                Intent intento = new Intent(MinijuegosMain.this, PreguntasMinijuego.class);
                startActivity(intento);
                preguntasJugado = true;
            }
        });
        btnCasino.setOnClickListener(view -> {
            Intent intento = new Intent(MinijuegosMain.this, CasinoMinijuego.class);
            startActivity(intento);
        });
        btnCartas.setOnClickListener(view -> {
            Intent intento = new Intent(MinijuegosMain.this, CartasMinijuego.class);
            startActivity(intento);
        });
    }

    private void gestionarBotones()
    {
        if (puntosGanados<TOPE)
        {
            for (int i=0; i<btns.size(); i++)
            {
                btns.get(i).setImageResource(R.drawable.minigame);
            }
        }
        else
        {
            abrirDialogo();

            for (int i=0; i<btns.size(); i++)
            {
                btns.get(i).setImageResource(R.drawable.minigameno);
            }

        }
    }

    // aviso si los puntos superan o llegan al tope
    public void abrirDialogo()
    {
        if(puntosGanados<TOPE)
        {
            String texto = getString(R.string.tope_alcanzado);
            int img = MegaClase.imgSegunDios("Loki","normal");
            dialogoAviso = new DialogoAviso(texto,"Tope alcanzado", img);
            dialogoAviso.show(getSupportFragmentManager(), "dialogo_tope_alcanzado");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(this.puntosGanados<TOPE)
        {

            //Cofres
            if(requestCode==1277 && resultCode==RESULT_OK)
            {
                int mas=data.getExtras().getInt("puntos");
                if(this.puntosGanados+mas<=TOPE) {
                    puntosGanados=puntosGanados+mas;
                    recargarPuntos();
                }
            }

            //Preguntas
            if(requestCode==1278 && resultCode==RESULT_OK)
            {
                this.jugadorDao.updatePreguntas(new Date());
                int mas=data.getExtras().getInt("puntos");
                if(this.puntosGanados+mas<=TOPE) {
                    recargarPuntos();
                }
            }

            gestionarBotones();
        }

    }

    private void recargarPuntos()
    {
        lblPuntos.setText(puntosGanados+" puntos de "+TOPE);
    }

}
