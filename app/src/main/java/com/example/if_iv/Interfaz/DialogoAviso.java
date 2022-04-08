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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.if_iv.R;

public class DialogoAviso extends DialogFragment {

        private EditText lblTexto;
        private ImageView img;

        // Interfaz de comunicación
        DialogoAviso.OnDialogoConfirmacionListener listener;

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //return super.onCreateDialog(savedInstanceState);

            //Creamos Alert Dialog personalizado
            AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialogo_aviso, null);
            builder.setView(dialogView)
                    .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            listener.onPossitiveButtonClick();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            listener.onNegativeButtonClick();
                            dialog.cancel();
                        }
                    });

            lblTexto = dialogView.findViewById(R.id.lblTexto);
            img = dialogView.findViewById(R.id.imgChibi);


            Dialog dialog =  builder.create();

            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }


        public void setLblTexto(String str){ lblTexto.setText(str); }
        public void setImg(Drawable draw){ img.setImageDrawable(draw); }


        //Interfaz para los botones Aceptar y Cancelar
        public interface OnDialogoConfirmacionListener{
            void onPossitiveButtonClick(); //Eventos Botón Positivos
            void onNegativeButtonClick();  //Eventos Botón Negativo
        }


        //Se debe añadir la interfaz para no tener el error
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            try{listener = (DialogoAviso.OnDialogoConfirmacionListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() +" no Implemento OnDialogoConfirmacionListener");
            }
        }
}
