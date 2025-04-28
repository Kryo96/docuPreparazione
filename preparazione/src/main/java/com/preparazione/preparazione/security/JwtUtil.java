package com.preparazione.preparazione.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY="12345678901234567890123456789012";

    private Key getSinginKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String user){
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSinginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateTokenAndRetrieveSubject(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSinginKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
