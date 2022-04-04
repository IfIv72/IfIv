package com.example.if_iv.Interfaz;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.if_iv.R;

import java.util.ArrayList;

public class CapitulosMain extends AppCompatActivity {

    private ArrayList<TextView> caps = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capitulos);

        caps.add(findViewById(R.id.cap0));
        caps.add(findViewById(R.id.cap1));

        comprobarBloqueos();
    }

    public void comprobarBloqueos()
    {
        GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_bordes_redondos);
        draw.setCornerRadius(15);

        for(TextView cap : caps)
        {
            String nom = cap.getText().toString();
            //consulta si el capitulo esta bloqueado
            String sql = "";
            boolean bloqueado = true;
            if(bloqueado)
            {
                cap.setEnabled(false);
                draw.setColor(ContextCompat.getColor(this,R.color.bloqueado));
                cap.setBackground(draw);
                cap.setOnClickListener(view -> {
                    Toast.makeText(CapitulosMain.this,"Bloqueado",Toast.LENGTH_SHORT);
                });
            }
            else
            {
                cap.setEnabled(true);
                draw.setColor(ContextCompat.getColor(this,R.color.capitulo));
                cap.setBackground(draw);
                cap.setOnClickListener(view -> {
                    // muestra dialogo o pantalla con info/opciones del capitulo
                });
            }
        }

    }

}
