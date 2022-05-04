package com.example.if_iv.Interfaz;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.if_iv.R;
import com.example.if_iv.dao.DiosDao;
import com.example.if_iv.model.Dios;
import com.example.if_iv.util.Megaclase;

import java.util.ArrayList;

public class PersonajesMain extends AppCompatActivity {

    //lista
    private ListView list;

    private DiosDao diosDao;
    private ArrayList<Dios> dioses;
    private DialogoPersonajes dialogo;
    private Megaclase meg = new Megaclase();
    private Context context;

    private final int MAX_AFINIDAD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personajes_main);

        getSupportActionBar().hide();
        context = this.getBaseContext();

        diosDao= new DiosDao(getBaseContext());
        dioses =  diosDao.findAll();

        list = findViewById(R.id.listPer);
        AdaptadorDioses adaptador=new AdaptadorDioses(this, dioses);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                // abrir dialogo con la info del dios
                Dios seleccionado = dioses.get(i);
                dialogo = new DialogoPersonajes(seleccionado,meg,context);
                dialogo.show(getSupportFragmentManager(), "dialogo_"+seleccionado.getNombre());
                Log.i("dios seleccionado",seleccionado.getNombre());
                Toast.makeText(getBaseContext(),seleccionado.getNombre()+":\n"+seleccionado.getInfo(),Toast.LENGTH_LONG).show();
            }
        });
    }

    class AdaptadorDioses extends ArrayAdapter <Dios>
    {
        public AdaptadorDioses(@NonNull Context context, ArrayList<Dios> dioses)
        {
            super(context, R.layout.listitem_personaje, dioses);
        }

        @NonNull
        @Override
        public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_personaje, null);

            //Estaria bien guardar aqui el dios
            Dios d= dioses.get(pos);

            // nombre dios
            TextView lblNombre = (TextView) item.findViewById(R.id.lblNombreP);
            lblNombre.setText(d.getNombre());
//            Log.i("I", d.getNombre());

            // imagen dios
            ImageView img = (ImageView) item.findViewById(R.id.imgPer);
            img.setImageResource(Megaclase.imgSegunDios(d.getNombre(),"normal"));
        /*
            //info dios
            TextView lblInfoP = item.findViewById(R.id.lblInfoP);
            lblInfoP.setText(d.getInfo());
        */
            // barra afinidad (fondo)
            GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_dialogo);

            TextView lblProgresoFondo = item.findViewById(R.id.lblEntero);
            int anchoPantalla = getResources().getDisplayMetrics().widthPixels;
            int anchoFondo = anchoPantalla - 100 - 45 ; // ancho_pantalla - (ancho_imagen + margen)
            lblProgresoFondo.setWidth(anchoFondo);
            lblProgresoFondo.setBackground(draw);

            // barra afinidad (relleno)
            TextView lblProgreso = item.findViewById(R.id.lblProgreso);
            int progreso = (d.getAfinidad()*anchoFondo) / MAX_AFINIDAD; // afinidad * anchoTotal / MAX_AFINIDAD
            lblProgreso.setWidth(progreso);  // varia segun la afinidad con el dios
            draw = (GradientDrawable) getDrawable(R.drawable.shape_relleno);
            draw.setColor(meg.colorSegun(d.getNombre(),context));
            lblProgreso.setBackgroundColor(meg.colorSegun(d.getNombre(),context));

            return (item);
        }
    }
}
