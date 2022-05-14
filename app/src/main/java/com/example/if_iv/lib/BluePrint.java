package com.example.if_iv.lib;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BluePrint<T> {

    private final Map<Integer, List<T>> prototype;

    public BluePrint(){
        prototype = new LinkedHashMap<>();
    }

    public void addToLayer(int layer, T object) {

        List<T> layerList = prototype.get(layer);
        if (layerList == null) {
            layerList = new LinkedList<>();
        }

        System.out.println("Setting " + object + " to layer " + layer);

        layerList.add(object);
        prototype.put(layer,layerList);

    }

    public List<List<T>> getLayers() {
        return new ArrayList<>(prototype.values());
    }
}
