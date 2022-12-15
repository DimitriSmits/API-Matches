package com.gamerdates.profileapi.mvc.models.user;

import com.gamerdates.profileapi.dal.entities.Profile;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class MatchCreateModel {

    private Long profile1;
    private Long profile2;
    private boolean love;

    public boolean getLove(){
        return this.love;
    }


}