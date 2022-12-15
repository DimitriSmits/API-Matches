package com.gamerdates.profileapi.jwt.authenticationtoken;

import java.util.Collection;
import java.util.Collections;

import com.gamerdates.profileapi.dal.entities.Gamer;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private Gamer gamer;
    private Object credentials;

    public JWTAuthenticationToken(Gamer gamer, Object token){
		super(Collections.emptyList());
		this.gamer = gamer;
        this.credentials = token;
		super.setAuthenticated(false);
    }
    
	public JWTAuthenticationToken(Gamer gamer, Object token, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.gamer = gamer;
        this.credentials = token;
		super.setAuthenticated(true);
	}

    @Override
    public Object getPrincipal() {
        return gamer;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    public Gamer getGamer(){
        return gamer;
    }
}
