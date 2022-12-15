package com.gamerdates.profileapi.exceptions;

public class InvalidJWTException extends JWTException {
    
    public InvalidJWTException(){
        super("Ongeldige JWT");
    }
    
    public InvalidJWTException(Throwable throwable){
        super("Ongeldige JWT", throwable);
    }

}
