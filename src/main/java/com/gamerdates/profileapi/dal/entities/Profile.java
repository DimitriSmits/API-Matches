package com.gamerdates.profileapi.dal.entities;

import javax.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column
    private EGender gender;

    @Enumerated(EnumType.STRING)
    @Column
    private EGender preferance;

    @Column
    private String picture_link;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Gamer  gamer;

    public Profile() {
        this.id = null;
        this.bio = null;
        this.gender = null;
        this.preferance = null;
        this.picture_link = null;
    }


    public Long id() {
        return this.id;
    }

    public Long getId(){return this.id;}

    public String getBio() {
        return this.bio;
    }

    public EGender getGender(){
        return this.gender;
    }

    public EGender getPreferance(){
        return this.preferance;
    }

    public String getPicture_link(){
        return this.picture_link;
    }

    public void setGamer(Gamer gamer){
        this.gamer = gamer;
    }

    public Gamer getGamer(){return this.gamer;}





}
