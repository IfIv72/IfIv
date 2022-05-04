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
import com.example.if_iv.util.Megaclase;

public class DialogoPersonajes extends DialogFragment {

    private RelativeLayout rel;
    private TextView lblTexto, lblNombre;
    private ImageView img;
    private Dios dios;
    private Megaclase meg;
    private Context context;

    public DialogoPersonajes(Dios d, Megaclase meg, Context con)
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

        View v = inflater.inflate(R.layout.dialogo_aviso, null);
        builder.setView(v)/*.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });*/;

        // colocar textos e imagenes
        lblNombre = v.findViewById(R.id.lblTitulo);
        lblNombre.setText(dios.getNombre());
        lblTexto = v.findViewById(R.id.lblTexto);
        lblTexto.setText(dios.getInfo());
        img = v.findViewById(R.id.imgChibi);
        img.setImageResource(Megaclase.imgSegunDios(dios.getNombre(), "normal"));

        // cambiar el color segun el dios
        rel = v.findViewById(R.id.relative);
        GradientDrawable draw = (GradientDrawable) rel.getBackground();
        draw.setStroke(4, meg.colorSegunDios(dios.getNombre(), context));
        rel.setBackground(draw);

        //Se cierra al tocarlo
        v.setOnClickListener(view -> {
            dismiss();
        });

        return builder.create();
    }

}
