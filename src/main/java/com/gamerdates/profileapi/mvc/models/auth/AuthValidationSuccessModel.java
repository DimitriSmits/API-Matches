package com.gamerdates.profileapi.mvc.models.auth;

public class AuthValidationSuccessModel extends AuthValidationModel {

    private Object model;

    public AuthValidationSuccessModel(Object model){
        this.model = model;
    }

    public Object getModel(){
        return model;
    }

    @Override
    public boolean isValid() {
        return true;
    }
    
}
