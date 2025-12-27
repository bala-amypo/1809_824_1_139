// package com.example.demo.service;

// import com.example.demo.entity.User;

// public interface UserService {

//     User registerUser(User user);

//     User getUserByEmail(String email);

//     User getUserById(Long id);
// }
package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Set;

public interface UserService {

    User registerUser(String email, String password, Set<String> roles);

    User getUserByEmail(String email);

    User getUserById(Long id);
}
