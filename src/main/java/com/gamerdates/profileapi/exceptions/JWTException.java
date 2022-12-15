package com.gamerdates.profileapi.exceptions;

public abstract class JWTException extends Exception {
    
    public JWTException(String message){
        super(message);
    }
    
    public JWTException(String message, Throwable throwable){
        super(message, throwable);
    }

}
