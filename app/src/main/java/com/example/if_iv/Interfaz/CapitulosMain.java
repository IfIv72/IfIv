package com.example.if_iv.Interfaz;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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

import me.jagar.mindmappingandroidlibrary.Views.Item;
import me.jagar.mindmappingandroidlibrary.Views.ItemLocation;
import me.jagar.mindmappingandroidlibrary.Views.MindMappingView;
import me.jagar.mindmappingandroidlibrary.Zoom.ZoomApi;
import me.jagar.mindmappingandroidlibrary.Zoom.ZoomLayout;

public class CapitulosMain extends AppCompatActivity {

    private HashMap<TextView,TextView> caps = new HashMap<TextView,TextView>();

    private CapituloDao capituloDao;
    private ArrayList<Capitulo> capitulos;

    private MindMappingView mindMap;
    private HashMap<Item, Capitulo> items;
    private ArrayList<Item> colocados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capitulos_main);

        getSupportActionBar().hide();

        capitulos=new ArrayList<Capitulo>();
        capituloDao= new CapituloDao(this.getBaseContext());
        cargarCapitulos();

        colocados=new ArrayList<Item>();
        mindMap= findViewById(R.id.mindMap);
//        mindMap.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.Action_)
//                {
//
//                }
//                return false;
//            }
//        });
        items= new HashMap<Item, Capitulo>();
        cargarMindMap();

        comprobarBloqueos();
    }

    public void comprobarBloqueos()
    {
        GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_nombre);
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
        this.capitulos= capituloDao.findAll();
    }

    private void cargarMindMap()
    {
        mindMap.setConnectionCircRadius(0);
        mindMap.setConnectionArgSize(0);
        Capitulo c=capituloDao.find(new Capitulo("0"));
        Item root=item(c);
        mindMap.addCentralItem(root, false);

        for (int i=1; i<capitulos.size();i++)
        {
            Item it=item(capitulos.get(i));
            if(colocados.contains(it)==false)
            {
                colocados.add(it);
                mindMap.addItem(it,root,100,15, ItemLocation.BOTTOM,false, null);
                cargarHijos(root, it);
            }
        }

    }

    private Item item(Capitulo capitulo)
    {
        Item i=new Item(CapitulosMain.this, capitulo.getNombre(),"", false);
        i.setBackgroundResource(R.drawable.shape_dialogo);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item i= (Item) v;
                Capitulo c= items.get(i);
                Log.i("item",c.getNombre());

            }
        });
        items.put(i,capitulo);
        return i;
    }

    private void cargarHijos(Item root, Item ite)
    {
        Capitulo c= items.get(root);
        ArrayList<Capitulo> hijos= c.getHijos();
        if(hijos!= null && hijos.size()>0)
        {
            Item last= null;
            for (int j=0; j<hijos.size();j++)
            {
                Item it= item(hijos.get(j));
                if(colocados.contains(it)==false)
                {
                    colocados.add(it);
                    mindMap.addItem(it,root, 100,15, ItemLocation.BOTTOM, false, null);
                }
                last=it;
            }

            root=last;

        }
        else
        {
            root=ite;
        }
    }

}
