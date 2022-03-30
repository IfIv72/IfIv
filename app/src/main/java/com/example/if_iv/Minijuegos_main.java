package com.example.if_iv;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Minijuegos_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dibujar();
    }

    // da formato a los shape
    public void dibujar()
    {
        Drawable draw = getDrawable(R.drawable.shape_bordes_redondos);
        //draw.setColorFilter(new ColorFilter());
    }

}
