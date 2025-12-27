// package com.example.demo.service;

// import com.example.demo.entity.User;

// public interface UserService {

//     User registerUser(User user);

//     User getUserByEmail(String email);

//     User getUserById(Long id);
// }

package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    String register(RegisterRequest request);
}