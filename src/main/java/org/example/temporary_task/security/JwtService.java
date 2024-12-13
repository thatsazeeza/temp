package org.example.temporary_task.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
@Service
public class JwtService {
    @Value("${temp.security.token.expiration}")
    private long expiration;

    @Value("${temp.security.token.secret}")
    private String secret;

    public String generateToken(String phoneNumber){
        Date now = new Date();
        Date expiration = Date.from(Instant.now().plusSeconds(this.expiration));
        return Jwts.builder().setSubject(phoneNumber).setIssuedAt(now).setExpiration(expiration).signWith(signingKey()).compact();
    }

    public Key signingKey(){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public Claims claims(String token){
        return Jwts.parserBuilder().setSigningKey(signingKey()).build().parseClaimsJwt(token).getBody();
    }
}
