package com.gamerdates.profileapi.dal.entities;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gamer {

    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    public Gamer(){
    this.id = id;
    this.username = getUsername();
    }

    public Gamer(String username, String password){
        this.username = username;
    }

    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String value){
        this.username = value;
    }

}