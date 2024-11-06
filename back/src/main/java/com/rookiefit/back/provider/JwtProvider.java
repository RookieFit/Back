package com.rookiefit.back.provider;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtProvider {

    @Value("${secret-key}") private String secretKey;

    public String create( String userId ){
        Date expireDate = Date.from(Instant.now().plus(1,ChronoUnit.HOURS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(userId)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .compact();
        return jwt;
    }

    public String validate( String jwt ){
        
        String subject = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

                subject = claims.getSubject();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return subject;
        
    }
}
