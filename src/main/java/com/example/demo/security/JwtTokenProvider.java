package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;

public class JwtTokenProvider {

    private final String SECRET = "secret-key";

    public String createToken(Long userId, String email, Set<String> roles) {
        return Jwts.builder()
                .claim("uid", userId)
                .claim("email", email)
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    public Long getUserId(String token) {
        return getClaims(token).get("uid", Number.class).longValue();
    }

    public Set<String> getRoles(String token) {
        return new HashSet<>(getClaims(token).get("roles", List.class));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token).getBody();
    }
}
