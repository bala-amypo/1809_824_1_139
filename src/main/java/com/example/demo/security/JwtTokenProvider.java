package com.example.demo.security;

import java.util.*;

public class JwtTokenProvider {

    public String createToken(Long userId, String email, Set<String> roles) {
        return userId + "|" + email + "|" + String.join(",", roles);
    }

    public boolean validateToken(String token) {
        return token != null && token.contains("|");
    }

    public String getEmail(String token) {
        return token.split("\\|")[1];
    }

    public Long getUserId(String token) {
        return Long.parseLong(token.split("\\|")[0]);
    }

    public Set<String> getRoles(String token) {
        return Set.of(token.split("\\|")[2].split(","));
    }
}
