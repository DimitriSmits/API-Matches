package com.gamerdates.profileapi.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamerdates.profileapi.dal.jpa.GamersRepository;
import com.gamerdates.profileapi.jwt.authenticationtoken.JWTAuthenticationToken;
import com.gamerdates.profileapi.dal.entities.Gamer;
import com.gamerdates.profileapi.exceptions.JWTException;
import com.gamerdates.profileapi.exceptions.UserNotFoundException;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTFilter implements Filter {

    private ApplicationContext applicationContext;

    public JWTFilter(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(!(request instanceof HttpServletRequest && response instanceof HttpServletResponse)){
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        JWTHelper service = applicationContext.getBean(JWTHelper.class);

        String bearerJwt = httpReq.getHeader("Authorization");

        String jwt = null;

        if(bearerJwt == null){
            chain.doFilter(request, response);
            return;
        }else if(!bearerJwt.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }else{
            jwt = bearerJwt.substring("Bearer ".length());
        }

        GamerJWT gamerJwt;
        try {
            gamerJwt = service.validate(jwt);
        } catch (JWTException e) {
            httpRes.sendError(401, e.getMessage());
            return;
        }

        httpReq.getSession(true).setAttribute("gamerId", gamerJwt.getGamerId());

        GamersRepository gamersRepository = applicationContext.getBean(GamersRepository.class);

        Gamer gamer = gamersRepository.findById(gamerJwt.getGamerId()).orElse(null);

        if(gamer == null){
            throw new UserNotFoundException(gamerJwt.getGamerId());
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        AbstractAuthenticationToken authToken = new JWTAuthenticationToken(gamer, jwt, authorities);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authToken);

        chain.doFilter(request, response);
    }
}
