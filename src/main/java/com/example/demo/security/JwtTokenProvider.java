

// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Collection;
// import java.util.Date;
// import java.util.HashSet;
// import java.util.Set;


// @Component
// public class JwtTokenProvider {

//     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//     private final long validityInMs = 60 * 60 * 1000;

//     public String createToken(Long userId, String email, Set<String> roles) {

//         Claims claims = Jwts.claims().setSubject(email);
//         claims.put("userId", userId);
//         claims.put("roles", roles);

//         Date now = new Date();
//         Date expiry = new Date(now.getTime() + validityInMs);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setIssuedAt(now)
//                 .setExpiration(expiry)
//                 .signWith(key)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                     .setSigningKey(key)
//                     .build()
//                     .parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public String getEmail(String token) {
//         return getClaims(token).getSubject();
//     }

//     public Long getUserId(String token) {
//         return getClaims(token).get("userId", Long.class);
//     }

    
//     public Set<String> getRoles(String token) {
//         Object roles = getClaims(token).get("roles");
//         Set<String> result = new HashSet<>();

//         if (roles instanceof Collection<?>) {
//             for (Object r : (Collection<?>) roles) {
//                 result.add(String.valueOf(r));
//             }
//         }

//         return result;
//     }

//     private Claims getClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }

package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtTokenProvider {

    private final Key key = Keys.hmacShaKeyFor(
            "MySecretKeyForJWTGeneration1234567890".getBytes()
    );

    public String generateToken(String username, Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return parse(token).getBody().getSubject();
    }

    public boolean validate(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return parse(token).getBody();
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
