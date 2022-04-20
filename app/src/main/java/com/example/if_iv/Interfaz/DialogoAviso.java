package com.example.if_iv.Interfaz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.if_iv.R;

public class DialogoAviso extends DialogFragment {

    private TextView lblTexto, lblTitulo;
    private ImageView img;
    private String auxTxt, auxTit;
    private int auxImg;

    public DialogoAviso(String texto, String titulo, int img)
    {
        auxTxt = texto;
        auxTit = titulo;
        auxImg = img;
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

        lblTitulo = v.findViewById(R.id.lblTitulo);
        lblTitulo.setText(auxTit);
        lblTexto = v.findViewById(R.id.lblTexto);
        lblTexto.setText(auxTxt);
        img = v.findViewById(R.id.imgChibi);
        img.setImageResource(auxImg);

        //Se cierra al tocarlo
        v.setOnClickListener(view -> {
            dismiss();
        });

        return builder.create();
    }

}