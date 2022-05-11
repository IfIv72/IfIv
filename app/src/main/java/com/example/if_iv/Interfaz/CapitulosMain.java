package com.example.if_iv.Interfaz;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.if_iv.R;
import com.example.if_iv.dao.CapituloDao;
import com.example.if_iv.model.Capitulo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import me.jagar.mindmappingandroidlibrary.Views.Item;
import me.jagar.mindmappingandroidlibrary.Views.ItemLocation;
import me.jagar.mindmappingandroidlibrary.Views.MindMappingView;

public class CapitulosMain extends AppCompatActivity {

    private HashMap<TextView,TextView> caps = new HashMap<>();

    private CapituloDao capituloDao;
    private ArrayList<Capitulo> capitulos;
    private HashMap<String,Item> capitulosInterfaz;
    private MindMappingView mindMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capitulos_main);

        getSupportActionBar().hide();

        // Recoger capitulos de la BD
        capitulos = new ArrayList<>();
        capituloDao = new CapituloDao(this.getBaseContext());
        cargarCapitulos();

        // Por cada capitulo, crear un item de interfaz y asociarlo al nombre
        capitulosInterfaz = new HashMap<>();
        capitulos.forEach(c -> capitulosInterfaz.put(c.getNombre(),item(c)));

        //crea padre fake
        Item padre = item(new Capitulo("-1","",false,"",""));
        capitulosInterfaz.put("-1",padre);

        // Dibujar el mindmap
        mindMap = findViewById(R.id.mindMap);
        cargarMindMap();
        //comprobarBloqueos();
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

    private void cargarCapitulos()
    {
        this.capitulos = capituloDao.findAll();
    }

    private void cargarMindMap()
    {
        // Settings del mindmap
        mindMap.setConnectionCircRadius(0);
        mindMap.setConnectionArgSize(0);
        mindMap.setConnectionArrowSize(0);

        //crear root
        Capitulo rootCap = capituloDao.find(new Capitulo("0"));
        Item root = item(rootCap);
        root.setVisibility(View.GONE);
        mindMap.addCentralItem(root, false);
        mindMap.setConnectionColor("#00000000");

        //cargar hijos
        capitulos.stream()
                .filter(c -> "0".equals(c.getNombre()) == false)
                .forEach(cap -> {
            System.out.println(cap.getNombre());
            if(cap.getNombre().length() == 1) {
                System.out.println("Cargando hijo " + cap.getNombre());
                Item padre = capitulosInterfaz.get(cap.getPadre());
                cargarHijos(cap, padre, true);
            }

        });

    }

    private void cargarHijos(Capitulo hijo, Item padre, boolean root){
        Item itemHijo = capitulosInterfaz.get(hijo.getNombre());

        int location = ItemLocation.RIGHT;

        if(root) {
            location = ItemLocation.LEFT;
        }


        mindMap.addItem(itemHijo,padre,0,0, location,false, null);

        List<Capitulo> nietos = hijo.getHijos();
        if(nietos == null) {
            return;
        }

        nietos.forEach(nieto -> {
           cargarHijos(nieto,itemHijo,false);
        });
    }

    private Item item(Capitulo capitulo)
    {
        Item i=new Item(CapitulosMain.this, capitulo.getNombre(), "", true);
        i.setBackgroundResource(R.drawable.shape_titulos);
        i.setMinimumWidth(200);

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item i= (Item) v;
                Capitulo c = capitulo;
                Log.i("item",c.getNombre());

            }
        });
        return i;
    }

    public void dibujarCapitulo(Capitulo c){

    }

}
