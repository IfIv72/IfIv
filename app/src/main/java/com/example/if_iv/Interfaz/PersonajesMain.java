package com.example.if_iv.Interfaz;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.if_iv.R;
import com.example.if_iv.model.Dios;
import com.example.if_iv.util.Megaclase;

import java.util.HashMap;

public class PersonajesMain extends AppCompatActivity {

    //lista
    private ListView list;
    private Dios[] nombres;
    private HashMap<String, Dios> dioses;  // <Nombre, dios>

    private final int MAX_AFINIDAD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personajes_main);

        list = findViewById(R.id.listPer);
        nombres = new Dios[3];
        llenarDioses();
        AdaptadorDioses adaptador=new AdaptadorDioses(this, nombres);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // abrir dialogo con la info del dios
                int posDios = list.getSelectedItemPosition();
                Dios seleccionado = dioses.get(nombres[posDios]);
            }
        });
    }

    // llena el array de dioses con los datos de la bbdd
    public void llenarDioses()
    {
        nombres[0] = new Dios("Apolo",20,"info","ruta","griego");
        nombres[1] = new Dios("Loki",50,"info","ruta","nordico");
        nombres[2] = new Dios("Anubis",100,"info","ruta","egipcio");
        dioses = new HashMap<String, Dios>();
    }

    class AdaptadorDioses extends ArrayAdapter <Dios>
    {
        public AdaptadorDioses(@NonNull Context context, Dios[] dioses)
        {
            super(context, R.layout.listitem_personaje, dioses);
        }

        @NonNull
        @Override
        public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_personaje, null);

            TextView lblNombre = (TextView) item.findViewById(R.id.lblNombreP);
            lblNombre.setText(nombres[pos].getNombre());
            Log.i("I", nombres[pos].getNombre());

            ImageView img = (ImageView) item.findViewById(R.id.imgPer);
            //img.setImageResource(R.drawable.cofre);
            img.setImageResource(Megaclase.imgSegunDios(nombres[pos].getNombre(),"chibi"));
            //img.setImageResource(dioses[pos].getRutaImg());

            TextView lblProgresoFondo = item.findViewById(R.id.lblEntero);
            int anchoPantalla = getResources().getDisplayMetrics().widthPixels;
            int anchoFondo = anchoPantalla - 100 - 40 ; // ancho_pantalla - (ancho_imagen + margen)
            lblProgresoFondo.setWidth(anchoFondo);

            TextView lblProgreso = item.findViewById(R.id.lblProgreso);
            int progreso = (nombres[pos].getAfinidad()*anchoFondo) / MAX_AFINIDAD; // afinidad * anchoTotal / MAX_AFINIDAD
            lblProgreso.setWidth(progreso);  // varia segun la afinidad con el dios
            lblProgreso.setBackgroundColor(Megaclase.colorSegunDios(nombres[pos].getNombre()));

            item.setOnClickListener(view -> {
                // abrir dialogo con la info del dios
            });

            return (item);
        }
    }
}
