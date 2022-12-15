package com.gamerdates.profileapi.mvc.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenRequest {

    public enum GrantType {
        REFRESH_TOKEN,
        AUTHORIZATION_CODE
    }
    
    private GrantType grantType;
    private String code;
    private String clientId;
    private String clientSecret;
    private String redirectUri;

    public RefreshTokenRequest(GrantType grantType, String code, String clientId, String clientSecret, String redirectUri){
        this.grantType = grantType;
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    @JsonProperty("grant_type")
    public String getGrantType(){
        if(this.grantType == GrantType.AUTHORIZATION_CODE){
            return "authorization_code";
        }
        return "refresh_token";
    }

    @JsonProperty("code")
    public String getCode(){
        return code;
    }

    @JsonProperty("client_id")
    public String getClientID(){
        return clientId;
    }

    @JsonProperty("client_secret")
    public String getClientSecret(){
        return clientSecret;
    }

    @JsonProperty("redirect_uri")
    public String getRedirectURI(){
        return redirectUri;
    }

    

}
