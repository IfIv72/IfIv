package com.example.if_iv.Interfaz;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.if_iv.R;

import java.util.HashMap;

public class CapitulosMain extends AppCompatActivity {

    private HashMap<TextView,TextView> caps = new HashMap<TextView,TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capitulos);

        getSupportActionBar().hide();


        //caps.put(findViewById(R.id.cap0),findViewById(R.id.cap0));
        caps.put(findViewById(R.id.cap1),findViewById(R.id.bloqCap1));
        caps.put(findViewById(R.id.cap2_1),findViewById(R.id.bloqCap2_1));
        caps.put(findViewById(R.id.cap2_2),findViewById(R.id.bloqCap2_2));

        comprobarBloqueos();
    }

    public void comprobarBloqueos()
    {
        GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_bordes_redondos);
        draw.setCornerRadius(15);

        for(TextView cap : caps.keySet())
        {
            cap.setTextColor(ContextCompat.getColor(this,R.color.white));

            String nom = cap.getText().toString();  // nombre del capitulo  ej:(Cap0)
            //consulta si el capitulo esta bloqueado
            String sql = "";


            TextView bloqueo = caps.get(cap);  // se muestra si el capitulo esta bloqueado
            draw.setColor(ContextCompat.getColor(this,R.color.bloqueado));
            bloqueo.setBackground(draw);
            bloqueo.setOnClickListener(view -> {
                Toast.makeText(CapitulosMain.this,"Bloqueado",Toast.LENGTH_SHORT).show();
            });

            boolean bloqueado = true;
            if(bloqueado == false)
            {
                bloqueo.setVisibility(View.GONE);
                cap.setTextColor(ContextCompat.getColor(this,R.color.black));
                cap.setOnClickListener(view -> {
                    Toast.makeText(CapitulosMain.this, cap.getText().toString(), Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}
