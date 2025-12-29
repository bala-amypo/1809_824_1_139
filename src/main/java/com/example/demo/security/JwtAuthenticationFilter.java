// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.List;

// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtTokenProvider jwtTokenProvider;

//     public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader != null && authHeader.startsWith("Bearer ")) {

//             String token = authHeader.substring(7);

//             if (jwtTokenProvider.validateToken(token)) {

//                 Long userId = jwtTokenProvider.getUserId(token);
//                 String email = jwtTokenProvider.getEmail(token);

//                 List<SimpleGrantedAuthority> authorities =
//                         jwtTokenProvider.getRoles(token).stream()
//                                 .map(SimpleGrantedAuthority::new)
//                                 .toList();

//                 UsernamePasswordAuthenticationToken authentication =
//                         new UsernamePasswordAuthenticationToken(
//                                 email,
//                                 null,
//                                 authorities
//                         );

//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }

package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import com.example.demo.service.UserService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider provider;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtTokenProvider provider, UserService userService) {
        this.provider = provider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (provider.validate(token)) {
                String username = provider.getUsername(token);
                UserDetails userDetails = userService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                auth.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}
