package com.gamerdates.profileapi.config;

import com.gamerdates.profileapi.jwt.JWTHelper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AuthAPITestConfiguration {

    @Bean
    public JWTHelper jwtHelper(@Value("${jwt.audience}") String jwtAudience, @Value("${jwt.domain}") String jwtDomain, @Value("${jwt.key}") String jwtKey){
        return new JWTHelper(jwtAudience, jwtDomain, jwtKey);
    }
}
