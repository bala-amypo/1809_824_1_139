package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = userService.findByEmail(request.getEmail());
            String token = tokenProvider.generateToken(user);

            AuthResponse response = new AuthResponse();
            response.setEmail(user.getEmail());
            response.setRole(user.getRole().name());
            response.setToken(token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException ex) {
            throw ex; // ApiExceptionHandler or Spring Security handles error response
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Password encoded in UserService
        user.setRole(User.Role.STUDENT); // Default role for registration

        User created = userService.registerUser(user);

        String token = tokenProvider.generateToken(created);

        AuthResponse response = new AuthResponse();
        response.setEmail(created.getEmail());
        response.setRole(created.getRole().name());
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}
