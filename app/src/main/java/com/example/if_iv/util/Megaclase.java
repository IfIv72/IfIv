package com.example.if_iv.util;


import com.example.if_iv.R;

// tiene metodos staticos que ayudaran al resto de clases para ahorrar lineas de codigo
public class Megaclase
{
  /////  IMAGENES y COLORES
    public static int imgSegunDios(String dios, String estado)
    {// descomentar al añadir las imagenes
        /*switch(dios)
        {
            case "Loki":
                if(estado.equals("chibi"))
                    return R.drawable.loki_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.loki_feliz;
                if(estado.equals("triste"))
                    return R.drawable.loki_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.loki_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.loki;
            case "Freya":
                if(estado.equals("chibi"))
                    return R.drawable.freya_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.freya_feliz;
                if(estado.equals("triste"))
                    return R.drawable.freya_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.freya_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.freya;
            case "Anubis":
                if(estado.equals("chibi"))
                    return R.drawable.anubis_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.anubis_feliz;
                if(estado.equals("triste"))
                    return R.drawable.anubis_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.anubis_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.anubis;
            case "Isis":
                if(estado.equals("chibi"))
                    return R.drawable.isis_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.isis_feliz;
                if(estado.equals("triste"))
                    return R.drawable.isis_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.isis_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.isis;
            case "Apolo":
                if(estado.equals("chibi"))
                    return R.drawable.apolo_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.apolo_feliz;
                if(estado.equals("triste"))
                    return R.drawable.apolo_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.apolo_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.apolo;
            case "Hermes":
                if(estado.equals("chibi"))
                    return R.drawable.hermes_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.hermes_feliz;
                if(estado.equals("triste"))
                    return R.drawable.hermes_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.hermes_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.hermes;
            case "Dionisio":
                if(estado.equals("chibi"))
                    return R.drawable.dionisio_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.dionisio_feliz;
                if(estado.equals("triste"))
                    return R.drawable.dionisio_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.dionisio_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.dionisio;
            case "iv":
                if(estado.equals("chibi"))
                    return R.drawable.iv_chibi;
                if(estado.equals("feliz"))
                    return R.drawable.iv_feliz;
                if(estado.equals("triste"))
                    return R.drawable.iv_triste;
                if(estado.equals("enfadado"))
                    return R.drawable.iv_enfadado;
                if(estado.equals("normal"))
                    return R.drawable.iv;
            default:
                return R.drawable.silueta;
        }*/
        return R.drawable.hoguera;
    }


    public static int colorSegunDios(String dios)
    {
        switch(dios) {
            case "Loki":
                return R.color.loki;
            case "Freya":
                return R.color.freya;
            case "Anubis":
                return R.color.anubis;
            case "Isis":
                return R.color.isis;
            case "Apolo":
                return R.color.apolo;
            case "Hermes":
                return R.color.hermes;
            case "Dionisio":
                return R.color.dionisio;
            case "iv":
                return R.color.iv;
            default:
                return R.color.black;
        }
    }




 ///// CONVERSORES (sqlite)
  /// Fechas




    //Booleans



}
