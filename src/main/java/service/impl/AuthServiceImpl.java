// package com.example.demo.service.impl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserService {

//     @Autowired
//     private UserRepository repository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Override
//     public User registerUser(User user) {

//         if (user.getEmail() == null || user.getEmail().isBlank())
//             throw new IllegalArgumentException("Email required");

//         if (user.getPassword() == null || user.getPassword().isBlank())
//             throw new IllegalArgumentException("Password required");

//         if (repository.findByEmail(user.getEmail()).isPresent())
//             throw new IllegalArgumentException("User already exists");

//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return repository.save(user);
//     }

//     @Override
//     public User getUserByEmail(String email) {
//         return repository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));
//     }

//     @Override
//     public User getUserById(Long id) {
//         return repository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("User not found"));
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder;

    public AuthServiceImpl(
            UserRepository userRepo,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String register(RegisterRequest request) {

        Role role = Role.valueOf(request.getRole().toUpperCase());

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles(Set.of("ROLE_" + role.name()));

        userRepo.save(user);
        return "User registered successfully";
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRoles().iterator().next()
        );
    }
}
