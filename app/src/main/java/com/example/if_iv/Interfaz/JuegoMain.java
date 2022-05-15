package com.example.if_iv.Interfaz;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.if_iv.R;
import com.example.if_iv.dao.DiosDao;
import com.example.if_iv.dao.EleccionesDao;
import com.example.if_iv.dao.JugadorDao;
import com.example.if_iv.model.Dialogo;
import com.example.if_iv.model.Dios;
import com.example.if_iv.model.Respuesta;
import com.example.if_iv.util.MegaClase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class JuegoMain extends AppCompatActivity implements DialogoNombre.DialogoNombreListener{

    private LinearLayout layDialogo, layEleccion, layContenedor;
    private TextView lblNombre, lblDialogo, lblResp1, lblResp2, lblResp3;
    private ImageView imgDios;
    private HashMap<String, Dialogo> conversacion;
    private Dialogo actual;
    private Dios mayorA;
    private Context context;
    private MegaClase meg = new MegaClase();

    private JugadorDao jugadorDao;
    private EleccionesDao eleccionesDao;
    private DiosDao diosDao;
    private String nombreJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_main);

        getSupportActionBar().hide();

        context = this.getBaseContext();
        jugadorDao= new JugadorDao(context);
        eleccionesDao=new EleccionesDao(context);
        diosDao=new DiosDao(context);
        nombreJugador=jugadorDao.find().getNombre();

        layContenedor = findViewById(R.id.layPadre);
        lblNombre = findViewById(R.id.lblNombre);
        imgDios = findViewById(R.id.imgDios);

        layDialogo = findViewById(R.id.layDialogo);
        lblDialogo = findViewById(R.id.lblDialogo);

        layEleccion = findViewById(R.id.layEleccion);
        lblResp1 = findViewById(R.id.resp1);
        lblResp2 = findViewById(R.id.resp2);
        lblResp3 = findViewById(R.id.resp3);

        // eventos
        layDialogo.setOnClickListener(view ->{
            // avanza al siguiente dialogo
            actual = conversacion.get(actual.getSiguiente());
            prepararDialogo();
        });
            //pasar dialogos
        lblResp1.setOnClickListener(view ->{
            // tratar la consecuencia
            char tipoCon = actual.getResp1().getTipoCon();
            String consecuencia = actual.getResp1().getConsecuencia();
            gestionarConsecuencia(tipoCon,consecuencia);

            // avanza al siguiente dialogo
            actual = conversacion.get(actual.getResp1().getSiguiente());
            prepararDialogo();
        });
        lblResp2.setOnClickListener(view ->{

            // tratar la consecuencia
            char tipoCon = actual.getResp2().getTipoCon();
            String consecuencia = actual.getResp2().getConsecuencia();
            gestionarConsecuencia(tipoCon,consecuencia);

            // avanza al siguiente dialogo
            actual = conversacion.get(actual.getResp2().getSiguiente());
            prepararDialogo();
        });
        lblResp3.setOnClickListener(view ->{

            // tratar la consecuencia
            char tipoCon = actual.getResp3().getTipoCon();
            String consecuencia = actual.getResp3().getConsecuencia();
            gestionarConsecuencia(tipoCon,consecuencia);

            // avanza al siguiente dialogo
            actual = conversacion.get(actual.getResp3().getSiguiente());
            prepararDialogo();
        });

        llenarConversacion("cap0.txt");
        prepararDialogo();
    }

    // carga el fichero en conversaciones
    public boolean llenarConversacion(String nomFich)
    {
        Log.i("Fichero","LLENANDO...");
        conversacion = new HashMap<String, Dialogo>();
        try
        {
            boolean primero = true;
            InputStreamReader fraw = new InputStreamReader(this.context.getAssets().open(nomFich));
            BufferedReader brin = new BufferedReader( fraw );
            String linea = brin.readLine();
            while (linea!=null)
            {
                Dialogo dialogo;
                String[] aux = linea.split(";");

                //construye el dialogo
                String cod = aux[0];
                char tipo = aux[1].charAt(0);
                String hablante = aux[2];

                if(tipo == 'd') // dialogo plano
                {
                    String estado = aux[3];
                    String txt = aux[4];
                    String siguiente = aux[5];
                    dialogo = new Dialogo(cod,tipo,hablante,estado,txt,siguiente);
                }
                else // eleccion
                {
                    Respuesta resp1 = new Respuesta(aux[3],aux[4].charAt(0),aux[5], aux[6]);
                    Respuesta resp2 = new Respuesta(aux[7],aux[8].charAt(0),aux[9], aux[10]);
                    Respuesta resp3 = new Respuesta(aux[11],aux[12].charAt(0),aux[13], aux[14]);
                    dialogo = new Dialogo(cod,tipo,hablante, resp1, resp2, resp3);
                }

                if(primero)  // guarda el primer dialogo
                {
                    actual = dialogo;
                    primero = false;
                }

                conversacion.put(cod,dialogo);  // aniade la linea convertida en Dialogo
                Log.i("pruebaFIc",linea);
                linea = brin.readLine();  // lee la siguiente linea
            }
            fraw.close();
        }
        catch (IOException ex) {
            Log.e ("Ficheros", "ERROR!!! al LEER--> "+nomFich);
            Log.e("EXCEPTION",ex.getMessage());
            return false;
        }
        return true;
    }


    public void prepararDialogo()
    {
        String nomDios = actual.getHablante();
        if(nomDios.equals("IV"))
        {
            nombreJugador=jugadorDao.find().getNombre();
            nomDios=nombreJugador;
        }
        lblNombre.setText(nomDios);

        // cambiar color segun el dios
        GradientDrawable draw = (GradientDrawable) lblNombre.getBackground();
        draw.setStroke(6, meg.colorSegun(nomDios,context));
        lblNombre.setBackground(draw);
        draw = (GradientDrawable) layContenedor.getBackground();
        draw.setStroke(6, meg.colorSegun(nomDios,context));
        meg.colorSegun(nomDios,context);
        layContenedor.setBackground(draw);

        if(actual.getTipo() == 'd')  //dialogo normal
        {
            layEleccion.setVisibility(View.GONE);
            layDialogo.setVisibility(View.VISIBLE);

            switch (actual.getCod())
            {
                case "0-1-3":
                    lblDialogo.setText(actual.getTexto()+nomDios);
                    break;
                case "0-1-23":
                    mayorA = diosDao.findMayorA();
                    switch (mayorA.getNombre()) {
                        case "Dionisio":
                            actual.setCodSiguiente("0-1-24");
                            break;
                        case "Freya":
                            actual.setCodSiguiente("0-1-29");
                            break;
                        case "Loki":
                            actual.setCodSiguiente("0-1-27");
                            break;
                        case "Apolo":
                            actual.setCodSiguiente("0-1-32");
                            break;
                        default:
                            break;
                    }
                    break;
                case "0-1-41":
                    lblDialogo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "Enhorabuena! Has terminado el primer capitulo", Toast.LENGTH_SHORT).show();
                            //Actualizacion de la BBDD
                            finish();
                        }
                    });
                    break;
            }
            lblDialogo.setText(actual.getTexto());
            imgDios.setImageResource(MegaClase.imgSegunDios(nomDios,actual.getEstado()));
        }
        else  // eleccion
        {
            layDialogo.setVisibility(View.GONE);
            layEleccion.setVisibility(View.VISIBLE);
            lblResp1.setText(actual.getResp1().getTexto());
            lblResp2.setText(actual.getResp2().getTexto());
            lblResp3.setText(actual.getResp3().getTexto());
            if(actual.getResp1().getConsecuencia().equals("nombre"))
            {
                lblResp2.setVisibility(View.GONE);
                lblResp3.setVisibility(View.GONE);
                DialogoNombre dn= new DialogoNombre();
                FragmentManager fragmentManager= getSupportFragmentManager();
                dn.show(fragmentManager,"Dialogo nombre");
            }
            else
            {
                lblResp2.setVisibility(View.VISIBLE);
                lblResp3.setVisibility(View.VISIBLE);
            }
        }

    }


    public void gestionarConsecuencia(char tipoCon, String con)
    {
        if(tipoCon == 'a') // llama a un metodo
        {
            Toast.makeText(JuegoMain.this,"metodo: "+con,Toast.LENGTH_LONG).show();
        }
        if(tipoCon == 'b') // actualiza la bbdd
        {
            eleccionesDao.hacerUpdate(con);
            Toast.makeText(JuegoMain.this,"bbdd: "+con,Toast.LENGTH_LONG).show();
        }
        if(tipoCon == 'c')  // carga un fichero nuevo
        {
            if(con.charAt(0) == actual.getCod().charAt(0))  // si es el mismo capitulo
            {
                // comprobar si cambia de capitulo
                if(!llenarConversacion(con))
                    Toast.makeText(JuegoMain.this,"fichero: "+con,Toast.LENGTH_LONG).show();
            }
            else  // cambia de capitulo
            {
                Toast.makeText(JuegoMain.this,"fichero: "+con,Toast.LENGTH_LONG).show();
            }

        }
    }

    //Interfaz Dialogo NO BORRAR AUN QUE ESTEN VACIOS
    @Override
    public void onPossitiveButtonClick() {
        nombreJugador=jugadorDao.find().getNombre();
        lblResp1.setText(nombreJugador);
        lblNombre.setText(nombreJugador);

    }

    @Override
    public void onNegativeButtonClick() {
        nombreJugador=jugadorDao.find().getNombre();
        lblResp1.setText(nombreJugador);
        lblNombre.setText(nombreJugador);
    }
}
