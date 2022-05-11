package com.example.if_iv.Interfaz;

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
import java.util.List;

import me.jagar.mindmappingandroidlibrary.Views.Item;
import me.jagar.mindmappingandroidlibrary.Views.ItemLocation;
import me.jagar.mindmappingandroidlibrary.Views.MindMappingView;

public class CapitulosMainPro extends AppCompatActivity {

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

        //crear lineas de conexion
        crearLineas();

        //comprobarBloqueos();
    }

    /**
     * Comprobar que capitulos estan bloqueados
     */
    public void comprobarBloqueos()
    {

    }

    /**
     * Carga los capitulos de la BD
     */
    private void cargarCapitulos()
    {
        this.capitulos = capituloDao.findAll();
    }

    /**
     * Crea el mapa de capitulos
     */
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

    /**
     * Carga los hijos de un capitulo
     * @param hijo capitulo a cargar
     * @param padre padre del capitulo
     * @param root si es el root o no
     */
    private void cargarHijos(Capitulo hijo, Item padre, boolean root){
        Item itemHijo = capitulosInterfaz.get(hijo.getNombre());

        int location = ItemLocation.RIGHT;

        if(root) {
            location = ItemLocation.LEFT;
        }


        mindMap.addItem(itemHijo,padre,100,20, location,false, null);

        List<Capitulo> nietos = hijo.getHijos();
        if(nietos == null) {
            return;
        }

        nietos.forEach(nieto -> {
           cargarHijos(nieto,itemHijo,false);
        });
    }

    /**
     * Crea un item de interfaz para un capitulo
     * @param capitulo capitulo a crear
     * @return item de interfaz
     */
    private Item item(Capitulo capitulo)
    {
        Item i=new Item(CapitulosMainPro.this, capitulo.getNombre(),"", false);
        i.setBackgroundResource(R.drawable.shape_titulos);
        i.setMinimumWidth(150);
        i.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        i.setBackgroundResource(R.drawable.shape_dialogo);
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


    public void crearLineas(){

        capitulos.forEach(capitulo -> {
            Item cap = capitulosInterfaz.get(capitulo.getNombre());
            Item siguiente = capitulosInterfaz.get(capitulo.getSiguiente());

            //crear linea de conexion
            if(siguiente != null && !capitulo.getNombre().equals("-1") && !capitulo.getNombre().equals("0")) {

                if(capitulo.getNombre().length() == 1) {
                    System.out.println("Conexion creada de " + capitulo.getNombre() + " a " + capitulo.getSiguiente());
                    mindMap.addCustomConnection(cap, ItemLocation.BOTTOM,siguiente,ItemLocation.TOP,null,10,"#000000",0,0);
                    return;
                }

                mindMap.addCustomConnection(cap, ItemLocation.LEFT,siguiente,ItemLocation.RIGHT,null,10,"#000000",0,0);

            }
        });


    }

}
