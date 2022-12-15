package com.gamerdates.profileapi.mvc.models.user;

import com.gamerdates.profileapi.dal.entities.Gamer;
import com.gamerdates.profileapi.mvc.models.SuperModel;

public class GamerModel extends SuperModel {

    private String username;

    public GamerModel(String username){

        this.username = username;

    }

    public String getUsername(){return username;}

    public static GamerModel from(Gamer gamer){
        return new GamerModel(gamer.getUsername());
    }
}
