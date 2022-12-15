package com.gamerdates.profileapi.mvc.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenRevokeRequest {
    
    private String token;

    public TokenRevokeRequest(String token){
        this.token = token;
    }

    @JsonProperty("token")
    public String getToken(){
        return token;
    }

}
