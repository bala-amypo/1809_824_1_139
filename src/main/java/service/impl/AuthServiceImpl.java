package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthService {

    User registerUser(User user);

    User findByEmail(String email);
}
