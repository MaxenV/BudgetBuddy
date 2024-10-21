package com.example.budget_buddy.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String secretKey = "your_secret_key";

    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email) // Generate token with email
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject(); // Return email from token
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
            return claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // Token is expired
        } catch (SignatureException e) {
            return true; // Token signature is invalid
        }
    }
}
