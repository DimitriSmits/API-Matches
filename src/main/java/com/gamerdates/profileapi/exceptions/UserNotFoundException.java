package com.gamerdates.profileapi.exceptions;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(Long patientId){
        super("Patient niet gevonden");
    }

}
