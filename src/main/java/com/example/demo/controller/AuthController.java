// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.*;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private AuthenticationManager authenticationManager;

//     @Autowired
//     private JwtTokenProvider jwtTokenProvider;

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @PostMapping("/login")
//     public Map<String, String> login(
//             @RequestParam String email,
//             @RequestParam String password) {

//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(email, password)
//         );

//         User user = userRepository.findByEmail(email).orElseThrow();

//         String token = jwtTokenProvider.createToken(
//                 user.getId(), user.getEmail(), user.getRoles()
//         );

//         return Map.of("token", token);
//     }

//     @PostMapping("/register")
//     public User register(@RequestParam String email,
//                          @RequestParam String password) {

//         User user = new User(
//                 email,
//                 passwordEncoder.encode(password),
//                 Set.of("ROLE_USER")
//         );

//         return userRepository.save(user);
//     }
// }
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}