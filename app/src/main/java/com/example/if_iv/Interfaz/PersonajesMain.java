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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.if_iv.R;
import com.example.if_iv.dao.DiosDao;
import com.example.if_iv.model.Dios;
import com.example.if_iv.util.MegaClase;

import java.util.ArrayList;

public class PersonajesMain extends AppCompatActivity {

    //lista
    private ListView list;

    private DiosDao diosDao;
    private ArrayList<Dios> dioses;
    private DialogoPersonajes dialogo;
    private MegaClase meg;  //A Ainara esto no le gusta
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personajes_main);

        getSupportActionBar().hide();
        context = this.getBaseContext();

        meg = new MegaClase();

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
                //Toast.makeText(getBaseContext(),seleccionado.getNombre()+":\n"+seleccionado.getInfo(),Toast.LENGTH_LONG).show();
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

            //guardar el dios
            Dios d= dioses.get(pos);

            // nombre dios
            TextView lblNombre = (TextView) item.findViewById(R.id.lblNombreP);
            lblNombre.setText(d.getNombre());
//            Log.i("I", d.getNombre());

            // imagen dios
            ImageView img = (ImageView) item.findViewById(R.id.imgPer);
            img.setImageResource(MegaClase.imgSegunDios(d.getNombre(),"normal"));

            //calcular tamaño total
            ImageView imgAux = item.findViewById(R.id.imgPer);

            int anchoPantalla = getResources().getDisplayMetrics().widthPixels;
            int anchoImg = imgAux.getWidth();
            int paddingImg = imgAux.getPaddingRight();
            int paddingLay = item.findViewById(R.id.layPer).getPaddingLeft() + item.findViewById(R.id.layPer).getPaddingRight();
            int anchoMaxBarra = anchoPantalla - (anchoImg + paddingImg + paddingLay);
            Log.i("Pantalla",""+anchoPantalla);

            //barra afinidad (borde/entero)
            TextView lblFondo = item.findViewById(R.id.lblEntero);
            lblFondo.setWidth(anchoMaxBarra);
            Log.i("Dios", d.getNombre());
            Log.i("Fondo",""+anchoMaxBarra);


            // barra afinidad (relleno)
            // tamano segun la afinidad
            TextView lblProgreso = item.findViewById(R.id.lblProgreso);
            int progreso = (int) Math.round( anchoMaxBarra * ( d.getAfinidad()/ 100.0)); //  anchoTotal * afinidad  / 100
            lblProgreso.setWidth(progreso);
            Log.i("Afinidad",""+d.getAfinidad());
            Log.i("Medida",""+progreso);
            //color segun el dios
            GradientDrawable draw = (GradientDrawable) getDrawable(R.drawable.shape_afinidad);
            draw.setColor(meg.colorSegun(d.getNombre(),context));
            lblProgreso.setBackgroundColor(meg.colorSegun(d.getNombre(),context));

            return (item);
        }
    }
}
