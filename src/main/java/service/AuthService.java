package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    void deleteUser(Long id);
}
