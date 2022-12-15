package com.gamerdates.profileapi.mvc.models.auth;

import com.gamerdates.profileapi.mvc.models.SuperModel;

public abstract class AuthValidationModel extends SuperModel {

    public abstract boolean isValid();

}
