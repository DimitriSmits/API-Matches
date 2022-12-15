package com.gamerdates.profileapi.mvc.models.auth;

public class AuthValidationFailureModel extends AuthValidationModel {

    @Override
    public boolean isValid() {
        return false;
    }
    
}
