package com.gamerdates.profileapi.jwt;

import java.time.Clock;
import java.time.Instant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gamerdates.profileapi.exceptions.InvalidJWTException;
import com.gamerdates.profileapi.exceptions.JWTException;

public class JWTHelper {

    private String audience;

    private Algorithm algorithm;

    public JWTHelper(String audience, String domain, String key){
        this.audience = audience;

        this.algorithm = Algorithm.HMAC256(key);
    }

    public GamerJWT validate(String token) throws JWTException {
        if(token == null){
            throw new InvalidJWTException();
        }
        try{
            JWTVerifier verifier = JWT
                .require(algorithm)
                .withAudience(audience)
                .ignoreIssuedAt()
                .acceptNotBefore(0)
                .acceptExpiresAt(0)
                .acceptLeeway(0)
                .build();

            DecodedJWT decoded = verifier.verify(token);

            Long userId = decoded.getClaim("gamerId").asLong();

            return new GamerJWT(userId);
        }catch(JWTVerificationException|NullPointerException e){
            throw new InvalidJWTException();
        }
    }

    public String create(GamerJWT gamer) throws IllegalArgumentException {
        Instant now = Instant.now(Clock.systemUTC());
        return JWT
            .create()
            .withClaim("nbf", now.getEpochSecond())
            .withClaim("exp", now.getEpochSecond() + 60 * 15)
            .withAudience(audience)
            .withClaim("gamerId", gamer.getGamerId())
            .sign(algorithm);
    }
}
