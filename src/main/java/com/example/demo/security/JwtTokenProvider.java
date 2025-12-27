// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import org.springframework.stereotype.Component;

// import java.util.*;

// @Component
// public class JwtTokenProvider {

//     private final String secret = "secretkey";

//     public String createToken(Long userId, String email, Set<String> roles) {
//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("uid", userId)
//                 .claim("roles", roles)
//                 .setIssuedAt(new Date())
//                 .signWith(SignatureAlgorithm.HS256, secret)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public String getEmail(String token) {
//         return getClaims(token).getSubject();
//     }

//     public Long getUserId(String token) {
//         return getClaims(token).get("uid", Long.class);
//     }

//     public Set<String> getRoles(String token) {
//         return new HashSet<>(getClaims(token).get("roles", List.class));
//     }

//     private Claims getClaims(String token) {
//         return Jwts.parser().setSigningKey(secret)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }

package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // ✅ Used by AuthController
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // Tests expect claims to exist
        claims.put(
                "roles",
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        return generateToken(userDetails.getUsername(), claims);
    }

    // ✅ Core generator
    public String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // ✅ MUST return false for invalid token (NO exception)
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ Tests read claims from token
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
