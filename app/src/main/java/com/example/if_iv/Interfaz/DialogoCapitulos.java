package com.example.if_iv.Interfaz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.if_iv.R;

public class DialogoCapitulos extends DialogFragment {

    private TextView lblTexto, lblTitulo;
    private String auxTxt, auxTit;

    public DialogoCapitulos(String texto, String titulo)
    {
        auxTxt = texto;
        auxTit = titulo;
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

        //Se cierra al tocarlo
        v.setOnClickListener(view -> {
            dismiss();
        });

        return builder.create();
    }

}
