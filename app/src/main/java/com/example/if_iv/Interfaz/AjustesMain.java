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
import androidx.fragment.app.FragmentManager;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.R;
import com.example.if_iv.dao.JugadorDao;
import com.example.if_iv.model.Jugador;

public class AjustesMain extends AppCompatActivity implements DialogoNombre.DialogoNombreListener{

    private JugadorDao jugadorDao;
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

        jugadorDao= new JugadorDao(getBaseContext());

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
        Jugador j=jugadorDao.find();
        lblPuntos.setText(String.valueOf(j.getPuntos()));
        lblLlaves.setText("0");
    }

    public void gestionarEventos()
    {
        //Puntos
        layPuntos.setOnClickListener(view -> {
            //dialogo confirmacion

        });

        //modo Oscuro
        modoOscuro.setOnClickListener(view -> {
            if(modoOscuro.isChecked())
            {
                Toast.makeText(this, "on", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "off", Toast.LENGTH_SHORT).show();
            }
        });


        //Cambiar nombre
        btnCambioNom.setOnClickListener(view -> {
            // conseguir nombre
            DialogoNombre dn= new DialogoNombre();
            FragmentManager fragmentManager= getSupportFragmentManager();
            dn.show(fragmentManager,"Dialogo nombre");
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
            BBDDSQLiteHelper bd=new BBDDSQLiteHelper(getBaseContext());
            bd.onUpgrade(bd.getWritableDatabase(),0,0);
            colocarPuntos();
        });

    }



    //Interfaz Dialogo NO BORRAR AUN QUE ESTEN VACIOS
    @Override
    public void onPossitiveButtonClick() {
        Toast.makeText(this, "Nombre actualizado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClick() {

    }
}
