

// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final JwtTokenProvider jwtTokenProvider;

//     public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                         "/auth/**",
//                         "/swagger-ui/**",
//                         "/swagger-ui.html",
//                         "/v3/api-docs/**"
//                 ).permitAll()
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().authenticated()
//             )

//             .addFilterBefore(
//                 new JwtAuthenticationFilter(jwtTokenProvider),
//                 UsernamePasswordAuthenticationFilter.class
//             );

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(
//             AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }

package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // ✅ Swagger URLs – MUST be public
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // ✅ Auth endpoints
                .requestMatchers("/api/auth/**").permitAll()

                // 🔒 All other APIs need JWT
                .anyRequest().authenticated()
            )

            // ✅ JWT filter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

