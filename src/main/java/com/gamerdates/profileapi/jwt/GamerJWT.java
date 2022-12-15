package com.gamerdates.profileapi.jwt;

public class GamerJWT {

    private long gamerId;

    public GamerJWT(long userId){
        this.gamerId = userId;
    }

    public long getGamerId(){
        return gamerId;
    }

}
