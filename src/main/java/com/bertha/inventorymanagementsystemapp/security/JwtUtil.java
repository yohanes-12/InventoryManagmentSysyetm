package com.bertha.inventorymanagementsystemapp.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
@Data
@AllArgsConstructor
public class JwtUtil {
    private final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String getToken(String email) {

        return Jwts.builder()
                .claim("email", email)
                .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(getSignKey())
                .compact();
    }

    public boolean validateToken(String token) throws JsonProcessingException {
        String userEmail = extractToken(token);
        return userEmail != null;
    }

    public String extractToken(String token) {
        String email = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getBody()
                .get("email", String.class);

        return email;
    }

    public SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

