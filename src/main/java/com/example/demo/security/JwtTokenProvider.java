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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMs;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return getUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}

