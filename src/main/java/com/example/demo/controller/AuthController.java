package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    // Constructor injection (MANDATORY for tests)
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    /**
     * POST /auth/login
     * Used in authentication test cases
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // Delegate authentication to Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email,
                        request.password
                )
        );

        // Fetch user after successful authentication
        User user = userService.findByEmail(request.email);

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole().name());

        // Build response
        AuthResponse response = new AuthResponse();
        response.token = token;
        response.email = user.getEmail();
        response.role = user.getRole().name();

        return response;
    }
}
