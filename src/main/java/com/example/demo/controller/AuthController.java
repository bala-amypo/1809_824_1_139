
// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.service.AuthService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     private final AuthService authService;

//     public AuthController(AuthService authService) {
//         this.authService = authService;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
//         return ResponseEntity.ok(authService.register(request));
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//         return ResponseEntity.ok(authService.login(request));
//     }
// }

package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final JwtTokenProvider provider;

    public AuthController(AuthenticationManager manager, JwtTokenProvider provider) {
        this.manager = manager;
        this.provider = provider;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.get("username"),
                        request.get("password")
                )
        );

        String token = provider.generateToken(
                auth.getName(),
                Map.of("role", auth.getAuthorities().iterator().next().getAuthority())
        );

        return Map.of("token", token);
    }
}
