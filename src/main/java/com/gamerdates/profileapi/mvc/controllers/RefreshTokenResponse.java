package com.gamerdates.profileapi.mvc.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenResponse {
    
    private String accessToken;
    private String refreshToken;
    private String tokenType;

    public RefreshTokenResponse(@JsonProperty("access_token") String accessToken, @JsonProperty("refresh_token") String refreshToken, @JsonProperty("token_type") String tokenType){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken(){
        return accessToken;
    }

    public String getRefreshToken(){
        return refreshToken;
    }

    public String getTokenType(){
        return tokenType;
    }
}
