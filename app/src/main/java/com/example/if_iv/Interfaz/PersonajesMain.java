package com.example.if_iv.Interfaz;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class PersonajesMain extends AppCompatActivity {

    //lista
    private ListView list;
    private Dios[] dioses;

    private final int MAX_AFINIDAD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personajes_main);

        list = findViewById(R.id.listPer);
        dioses = new Dios[3];
        llenarDioses();
        AdaptadorDioses adaptador=new AdaptadorDioses(this, dioses);
        list.setAdapter(adaptador);

    }

    // llena el array de dioses con los datos de la bbdd
    public void llenarDioses()
    {
        dioses[0] = new Dios("Apolo",20,"info","ruta","griego");
        dioses[1] = new Dios("Loki",50,"info","ruta","nordico");
        dioses[2] = new Dios("Anubis",100,"info","ruta","egipcio");
    }

    class AdaptadorDioses extends ArrayAdapter <Dios>
    {
        public AdaptadorDioses(@NonNull Context context, Dios[] dioses)
        {
            super(context, R.layout.listitem_personaje, dioses);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_personaje, null);

            TextView lblNombre = (TextView) item.findViewById(R.id.lblNombreP);
            lblNombre.setText(dioses[position].getNombre());
            Log.i("I",dioses[position].getNombre());

            ImageView img = (ImageView) item.findViewById(R.id.imgPer);
            img.setImageResource(R.drawable.cofre);
            //img.setImageResource(Megaclase.imgSegunDios(dioses[position].getNombre(),"chibi"));
            //img.setImageResource(dioses[position].getRutaImg());

            TextView lblProgresoFondo = item.findViewById(R.id.lblEntero);
            int anchoPantalla = getResources().getDisplayMetrics().widthPixels;
            int anchoFondo = anchoPantalla - 100 - 40 ; // ancho_pantalla - (ancho_imagen + margen)
            lblProgresoFondo.setWidth(anchoFondo);

            TextView lblProgreso = item.findViewById(R.id.lblProgreso);
            int progreso = (dioses[position].getAfinidad()*anchoFondo) / MAX_AFINIDAD; // afinidad * anchoTotal / MAX_AFINIDAD
            lblProgreso.setWidth(progreso);  // varia segun la afinidad con el dios


            item.setOnClickListener(view -> {
                // abrir dialogo con la info del dios
            });

            return (item);
        }
    }
}
