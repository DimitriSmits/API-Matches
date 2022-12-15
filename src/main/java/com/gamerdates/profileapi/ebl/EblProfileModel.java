package com.gamerdates.profileapi.ebl;

import java.util.Set;

//import com.pharmapartners.settingsapi.mvc.models.SettingsModel;

public class EblProfileModel {

    private Long patientId;
    private String username;

    public EblProfileModel(){

    }

    public EblProfileModel(Long patientId, String username){
        this.patientId = patientId;
        this.username = username;
    }

    public Long getPatientId(){
        return patientId;
    }

    public String getUsername(){
        return username;
    }

}
