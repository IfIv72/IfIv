package com.example.if_iv.Minijuegos;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.if_iv.R;
import com.example.if_iv.dao.JugadorDao;
import com.example.if_iv.model.Pregunta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PreguntasMinijuego extends AppCompatActivity {

    private HashMap<Integer, Pregunta> preguntas;
    private ArrayList<Pregunta> mostradas;
    private Pregunta actual;
    private int aciertos, cantPregun;
    private TextView lblEnun,lblOp1,lblOp2,lblOp3;
    private Context context;
    private JugadorDao jugadorDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntas_minijuegos);

        getSupportActionBar().hide();
        context = this.getBaseContext();

        jugadorDao= new JugadorDao(context);

        cantPregun = 1;
        aciertos = 0;

        lblEnun = findViewById(R.id.enunciado);
        lblOp1 = findViewById(R.id.resp1);
        lblOp2 = findViewById(R.id.resp2);
        lblOp3 = findViewById(R.id.resp3);

        mostradas = new ArrayList<Pregunta>();

        lblOp1.setOnClickListener(view -> {
            Log.i("P",lblOp1.getText().toString());
            comprobarRespuesta(lblOp1.getText().toString(), lblOp1);
        });
        lblOp2.setOnClickListener(view -> {
            Log.i("P",lblOp2.getText().toString());
            comprobarRespuesta(lblOp2.getText().toString(), lblOp2);
        });
        lblOp3.setOnClickListener(view -> {
            Log.i("P",lblOp3.getText().toString());
            comprobarRespuesta(lblOp3.getText().toString() ,lblOp3);
        });

        llenarPreguntas();
        mostrarPregunta();
    }

    public void mostrarPregunta()
    {
        int aux = (int) (Math.random()*preguntas.size()+1); // pregunta al azar
        Pregunta pregun = preguntas.get(aux);
        if(mostradas.contains(pregun)) // si ya se ha mostrado se busca otra
            mostrarPregunta();
        else  // si no esta repe se muestra
        {
            mostradas.add(pregun);
            actual = pregun;
            lblEnun.setText(pregun.getPregunta());

            // colocar aleatoriamente la respuesta correcta
            aux = (int) (Math.random()*3);
            if(aux == 0)
            {
                lblOp1.setText(actual.getCorrecta());
                lblOp2.setText(actual.getOp2());
                lblOp3.setText(actual.getOp1());
            }
            else
            {
                if(aux == 1)
                {
                    lblOp2.setText(actual.getCorrecta());
                    lblOp1.setText(actual.getOp2());
                    lblOp3.setText(actual.getOp1());
                }
                else
                {
                    lblOp3.setText(actual.getCorrecta());
                    lblOp2.setText(actual.getOp2());
                    lblOp1.setText(actual.getOp1());
                }
            }
        }
    }

    public void comprobarRespuesta(String resp, TextView op) {
        Log.i("cant:"," "+cantPregun);
        if(actual.getCorrecta().equals(resp))
        {
            op.setTextColor(getColor(R.color.hermes));
            aciertos++;
            YoYo.with(Techniques.Pulse).duration(1000).repeat(0).playOn(op);
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    op.setTextColor(getColor(R.color.gray));
                    //cuando pase el tiempo de bajo
                    if(cantPregun < 3)  // responde 3 preguntas
                        mostrarPregunta();
                    else
                        terminarJuego();
                    cantPregun++;
                }
            }, 1200); // tiempo de la animacion
        }
        else
        {
            YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(op);
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //cuando pase el tiempo de bajo
                    if(cantPregun < 3)  // responde 3 preguntas
                        mostrarPregunta();
                    else
                        terminarJuego();
                    cantPregun++;
                }
            }, 1200); // tiempo de la animacion
        }
    }

    public void terminarJuego()
    {
//        Toast.makeText(PreguntasMinijuego.this,"Aciertos: "+aciertos,Toast.LENGTH_SHORT).show();
        jugadorDao.updatePreguntas(new Date());
        finish();
        //////   AQUIIIIII
    }

    public void llenarPreguntas()
    {
        preguntas = new HashMap<Integer,Pregunta>();
        try
        {
            context = this.getBaseContext();
            InputStreamReader fraw = new InputStreamReader(this.context.getAssets().open("preguntas"));
            BufferedReader brin = new BufferedReader( fraw );
            String linea = brin.readLine();
            int cod = 0;
            while (linea!=null)
            {
                cod++;
                Pregunta pregun;
                String[] aux = linea.split(";");

                //construye el pregunta
                String text = aux[0];
                String op1 = aux[1];
                String op2 = aux[2];;
                String correcta = aux[3];
                pregun = new Pregunta(text,op1,op2,correcta);

                preguntas.put(cod,pregun);  // aniade pregunta
                linea = brin.readLine();  // lee la siguiente linea
            }
            fraw.close();
        }
        catch (IOException ex) {
            Log.e ("Ficheros", "ERROR!!! al LEER--> preguntas");
            Log.e("EXCEPTION",ex.getMessage());
        }
    }

}
