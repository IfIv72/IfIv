package com.example.if_iv.Interfaz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.if_iv.R;
import com.example.if_iv.dao.JugadorDao;


public class DialogoNombre extends DialogFragment {

    private DialogoNombreListener listener;
    private EditText edtNombre;
    private String nombre;
    private JugadorDao jugadorDao;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View d= inflater.inflate(R.layout.dialogo_nombre, null);
        builder.setView(d);
        builder.setMessage("")
                .setTitle("Cambia el nombre de tu personaje")
                .setPositiveButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String n=edtNombre.getText().toString();
                                if(n.length()>0)
                                {
                                    nombre=n;
                                    jugadorDao= new JugadorDao(getContext());
                                    jugadorDao.updateNombre(nombre);
                                }
                                listener.onPossitiveButtonClick();
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onNegativeButtonClick();
                            }
                        });

        edtNombre= d.findViewById(R.id.edtNombre);
//        return super.onCreateDialog(savedInstanceState);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (DialogoNombreListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " no Implemento DialogoNombreListener");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public interface DialogoNombreListener{
        void onPossitiveButtonClick(); //Eventos Botón Positivos
        void onNegativeButtonClick(); //Eventos Botón Negativo
    }
}
