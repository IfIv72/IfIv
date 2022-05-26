package com.example.if_iv.Interfaz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.if_iv.R;
import com.example.if_iv.model.Dios;
import com.example.if_iv.util.MegaClase;

public class DialogoPersonajes extends DialogFragment {

    private RelativeLayout rel;
    private TextView lblTexto, lblNombre, lblMito;
    private ImageView img;
    private Dios dios;
    private MegaClase meg;
    private Context context;

    public DialogoPersonajes(Dios d, MegaClase meg, Context con)
    {
        this.dios = d;
        this.meg = meg;
        this.context = con;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialogo_personajes, null);
        builder.setView(v)/*.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });*/;

        // colocar textos e imagenes
        lblNombre = v.findViewById(R.id.titNombre);
        lblNombre.setText(dios.getNombre());

        lblMito = v.findViewById(R.id.lblMito);
        lblMito.setText("Afinidad: "+dios.getMitologia());
        lblMito.setTextColor(meg.colorSegun(dios.getMitologia(),context));

        lblTexto = v.findViewById(R.id.lblTexto);
        lblTexto.setText(dios.getInfo());
        img = v.findViewById(R.id.imgDios);
        img.setImageResource(MegaClase.imgSegunDios(dios.getNombre(), "normal"));

        // cambiar el color segun el dios
        rel = v.findViewById(R.id.relative);
        GradientDrawable draw = (GradientDrawable) rel.getBackground();  //nullPointerException
        draw.setStroke(4, meg.colorSegun(dios.getNombre(), context));
        rel.setBackground(draw);

        //Se cierra al tocarlo
        v.setOnClickListener(view -> {
            dismiss();
        });

        return builder.create();
    }

}
