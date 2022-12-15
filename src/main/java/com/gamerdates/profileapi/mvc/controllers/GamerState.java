package com.gamerdates.profileapi.mvc.controllers;

import java.time.Instant;

public class GamerState {
    private Instant instant;
    private Long userId;

    public GamerState(Instant instant, Long userId){
        this.instant = instant;
        this.userId = userId;
    }

    public Instant getInstant(){
        return instant;
    }

    public Long getUserId(){
        return userId;
    }

}
