package com.example.if_iv.exception;

public class NotOrdenableException  extends NumberFormatException {
    public NotOrdenableException(String message){
        super(message);
    }

    public NotOrdenableException(){
        super();
    }
}
