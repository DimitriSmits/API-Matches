package com.gamerdates.profileapi.configuration.security;

import java.util.Arrays;

import com.gamerdates.profileapi.jwt.JWTFilter;
import com.gamerdates.profileapi.jwt.JWTHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Profile({ "local" })
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.audience}")
    private String jwtAudience;

    @Value("${jwt.domain}")
    private String jwtDomain;

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .addFilterAfter(new JWTFilter(applicationContext), BasicAuthenticationFilter.class)
            // .addFilterBefore(new JWTFilter(applicationContext), SessionManagementFilter.class)
            .cors()
                .and()
            .csrf()
                .disable()
            .anonymous()
                .authorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_ANONYMOUS")))
                .and()
            .authorizeRequests()
                .antMatchers("/test/send**").hasAnyAuthority("ROLE_PATIENT")
                .antMatchers("/auth/refresh").hasAnyAuthority("ROLE_ANONYMOUS")
                .antMatchers("/auth/login/patient", "/auth/login/patient/**").hasAnyAuthority("ROLE_ANONYMOUS")
                .antMatchers("/auth/login/gamer", "/auth/login/gamer/**").hasAnyAuthority("ROLE_ANONYMOUS")
                .antMatchers("/auth/login/**", "/auth/login/**").hasAnyAuthority("ROLE_ANONYMOUS")
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/profile/**").permitAll()
                .antMatchers("/match/**").permitAll()
                .antMatchers("/auth/login/refresh").hasAnyAuthority("ROLE_UNAUTH_PATIENT", "ROLE_PATIENT")
                .antMatchers("/auth/login/google").hasAnyAuthority("ROLE_UNAUTH_PATIENT")
                .antMatchers("/auth/login/google/code").hasAnyAuthority("ROLE_UNAUTH_PATIENT")
                .anyRequest().hasAnyAuthority("ROLE_GAMER");
    }

    @Bean
    public JWTHelper jwtHelper(){
        return new JWTHelper(jwtAudience, jwtDomain, jwtKey);
    }
}
