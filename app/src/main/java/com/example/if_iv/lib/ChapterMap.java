package com.example.if_iv.lib;

import com.example.if_iv.model.Capitulo;
import com.example.if_iv.util.AtomicInteger;

import java.util.List;

public class ChapterMap {

    public static BluePrint<Capitulo> createMapBlueprint(List<Capitulo> capitulos) {

        final BluePrint<Capitulo>  bluePrint = new BluePrint<>();

        AtomicInteger atomicLength = new AtomicInteger(1);
        AtomicInteger atomicCounter = new AtomicInteger(0);

        capitulos.stream().sorted(ChapterMap::order).forEach(chapter -> {
            int codeLength = chapter.getCodigo().length();

            System.out.println();
            System.out.println("New code length: " + codeLength);
            System.out.println("Last code length: " + atomicLength);
            System.out.println("Counter: " + atomicCounter);

            // subchilds
            if(codeLength > atomicLength.getValue() || codeLength < atomicLength.getValue()) {
                atomicCounter.add();
                bluePrint.addToLayer(atomicCounter.getValue(),chapter);
                atomicLength.setValue(codeLength);
                return;
            }

            // same layer
            bluePrint.addToLayer(atomicCounter.getValue(),chapter);
        });

        return bluePrint;
    }

    public static int order(Capitulo one, Capitulo two) {

        String code = one.getCodigo();
        String codeTwo = two.getCodigo();

        if(code == null || codeTwo == null){
            throw new NumberFormatException("No se pudo ordenar, código de capítulo mal construido");
        }

        if(code.length() == codeTwo.length()) {
            final String xstr = String.join("",code.split("\\."));
            final String ystr = String.join("",codeTwo.split("\\."));

            try {
                final int x = Integer.parseInt(xstr);
                final int y = Integer.parseInt(ystr);
                return Integer.compare(x,y);
            } catch(NumberFormatException e) {
                throw new NumberFormatException("No se pudo ordenar, código de capítulo mal construido");
            }
        }

        // compare strings
        if(code.length() > codeTwo.length()){
            code = code.substring(0,codeTwo.length());
            final int result = compareCodesByIntegerConversion(code,codeTwo);
            return (result == 0) ? 1 : result;
        }

        codeTwo = codeTwo.substring(0,code.length());
        final int result = compareCodesByIntegerConversion(code,codeTwo);
        return (result == 0) ? -1 : result;
    }

    public static int compareCodesByIntegerConversion(String code, String codeTwo){
        final String xstr = String.join("",code.split("\\."));
        final String ystr = String.join("",codeTwo.split("\\."));

        try {
            final int x = Integer.parseInt(xstr);
            final int y = Integer.parseInt(ystr);
            return Integer.compare(x,y);
        } catch(NumberFormatException e) {
            throw new NumberFormatException("No se pudo ordenar, código de capítulo mal construido");
        }
    }
}
