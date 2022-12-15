package com.gamerdates.profileapi.dal.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints=
@UniqueConstraint(columnNames = {"profile1_id", "profile2_id"}))
public class Match {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Profile  profile1;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Profile  profile2;

    private boolean love;

    public Match() {
        this.id = null;
        this.profile1 = null;
        this.profile2 = null;
        this.love = false;
    }


    public Long id() {
        return this.id;
    }

    public Long getId() {return this.id;}

    public Profile getProfile1() {
        return this.profile1;
    }

    public Profile getProfile2() {
        return this.profile2;
    }

    public boolean love(){
        return this.love;
    }

    public void setProfile1(Profile profile){
        this.profile1 = profile;
    }

    public void setProfile2(Profile profile){
        this.profile2 = profile;
    }

    public void setLove(boolean love)       {this.love = love;}
}