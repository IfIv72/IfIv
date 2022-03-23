package com.example.if_iv.Ayudas;

public class Boolean01 {

    //Esto nos va a dar problemas a la hora de hacer las pruebas por que siempre da un valor valido jeje
    public static boolean gestionInt(Integer inte)
    {
        if(inte==0)
            return false;
        return true;
    }

    public static Integer gestionBoolean(Boolean boo)
    {
        if(boo)
            return 1;
        return 0;
    }

}
