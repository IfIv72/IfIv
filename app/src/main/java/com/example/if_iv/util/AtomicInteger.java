package com.example.if_iv.util;

public class AtomicInteger {

    private int value;

    public AtomicInteger(){
        this.value = 0;
    }

    public AtomicInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void add(){
        this.add(1);
    }

    public void add(int value){
        this.value += value;
    }

    public void remove() {
        this.remove(1);
    }

    public void remove(int value) {
        this.add(-value);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.value).toString();
    }
}
