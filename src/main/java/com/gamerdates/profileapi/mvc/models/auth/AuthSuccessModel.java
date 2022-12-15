package com.gamerdates.profileapi.mvc.models.auth;

import com.gamerdates.profileapi.mvc.models.SuperModel;

public class AuthSuccessModel extends SuperModel {
    
    private String token;

    public AuthSuccessModel(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

}
