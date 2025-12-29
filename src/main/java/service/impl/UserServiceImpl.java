

// package com.example.demo.service.impl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepo;
//     private final BCryptPasswordEncoder encoder;

//     public UserServiceImpl(UserRepository userRepo) {
//         this.userRepo = userRepo;
//         this.encoder = new BCryptPasswordEncoder();
//     }

//     @Override
//     public User register(User user) {

//         userRepo.findByEmail(user.getEmail())
//                 .ifPresent(u -> { throw new IllegalArgumentException("Email already exists"); });

//         user.setPassword(encoder.encode(user.getPassword()));
//         return userRepo.save(user);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
