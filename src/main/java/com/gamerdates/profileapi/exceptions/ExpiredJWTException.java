package com.gamerdates.profileapi.exceptions;

public class ExpiredJWTException extends JWTException {
    
    public ExpiredJWTException(){
        super("JWT is komen te vervallen");
    }
    
    public ExpiredJWTException(Throwable throwable){
        super("JWT is komen te vervallen", throwable);
    }

}
