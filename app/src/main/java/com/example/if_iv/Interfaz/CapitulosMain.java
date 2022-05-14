package com.example.if_iv.Interfaz;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.if_iv.BBDD.BBDDSQLiteHelper;
import com.example.if_iv.R;
import com.example.if_iv.dao.CapituloDao;
import com.example.if_iv.lib.BluePrint;
import com.example.if_iv.lib.ChapterMap;
import com.example.if_iv.lib.LineView;
import com.example.if_iv.model.Capitulo;
import com.example.if_iv.util.AtomicInteger;
import com.example.if_iv.util.ResponsiveTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.jagar.mindmappingandroidlibrary.Views.Item;
import me.jagar.mindmappingandroidlibrary.Views.MindMappingView;

public class CapitulosMain extends AppCompatActivity {

    private HashMap<TextView,TextView> caps = new HashMap<>();
    private BBDDSQLiteHelper bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capitulos_main);
        getSupportActionBar().hide();

        this.bdHelper = new BBDDSQLiteHelper(getBaseContext());

        // Recoger capitulos de la BD
        final CapituloDao dao = new CapituloDao(bdHelper);
        final ArrayList<Capitulo> capitulos = (ArrayList<Capitulo>) cargarCapitulos(dao);

        // Crear mindmmap
        final BluePrint<Capitulo> print = ChapterMap.createMapBlueprint(capitulos);
        final  BluePrint<Button> buttonPrint = new BluePrint<Button>();

        // Dibujar el mindmap
        ConstraintLayout view = findViewById(R.id.map);
        cargarMindMap(print.getLayers(), buttonPrint, view);

        //crear lineas de conexion
        crearLineas(buttonPrint.getLayers(), view);

        //comprobarBloqueos();
    }

    /**
     * Comprobar que capitulos estan bloqueados
     */
    public void comprobarBloqueos() {
    }

    /**
     * Carga los capitulos de la BD
     * @return
     */
    private static List<Capitulo> cargarCapitulos(CapituloDao dao)
    {
       return dao.findAll();
    }

    /**
     * Crea el mapa de capitulos
     * @param capas Las capas del mapa
     * @param view La vista del mapa
     */
    private void cargarMindMap(List<List<Capitulo>> capas, BluePrint<Button> buttonPrint,ConstraintLayout view) {

        final int DEFAULT_WIDTH = 300;
        final int DEFAULT_HEIGHT = 200;
        final int DEFAULT_Y_OFFSET = 100;
        final int DEFAULT_X_PADDING = 50;
        final int DEFAULT_Y_PADDING = 50;

        view.setBackgroundResource(R.drawable.defaultfondoblur);

        AtomicInteger contadorY = new AtomicInteger(0);
        capas.forEach(capa -> {

            AtomicInteger contadorX = new AtomicInteger(0);
            capa.forEach(capitulo -> {

                // Crear capitulo button
                Button boton = new Button(this);
                boton.setText("" + capitulo.getCodigo());
                boton.setBackgroundResource(R.drawable.shape_bordes_redondos);
                boton.setPadding(DEFAULT_X_PADDING,DEFAULT_Y_PADDING,DEFAULT_X_PADDING,DEFAULT_Y_PADDING);
                boton.setWidth(DEFAULT_WIDTH - DEFAULT_X_PADDING);
                boton.setAlpha(.65f);

                // Coordenadas del boton
                final int displayWidth = ResponsiveTools.getDisplayWidth(view);
                boton.setX(contadorX.getValue() * DEFAULT_WIDTH + displayWidth/2 - (DEFAULT_WIDTH/2) * capa.size());
                boton.setY(contadorY.getValue() * DEFAULT_HEIGHT + DEFAULT_Y_OFFSET);

                // funciones de boton
                boton.setOnClickListener(v -> {
                    Toast toast = Toast.makeText(getApplicationContext(),"Click en capitulo " + capitulo.getCodigo(),Toast.LENGTH_SHORT);
                    toast.show();
                });

                buttonPrint.addToLayer(contadorY.getValue(),boton);

                view.addView(boton, contadorX.getValue());
                contadorX.add();
            });

            contadorY.add();
        });


    }

    /**
     * Crear las lineas entre capitulos
     */
    public void crearLineas(List<List<Button>> capas, ConstraintLayout view) {

    }

}
