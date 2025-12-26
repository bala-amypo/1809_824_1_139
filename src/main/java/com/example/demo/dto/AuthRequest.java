package com.example.demo.dto;
import java.util.Set;

public class AuthRequest {
    private String email;
    private String password;
    private Set<String> roles; // Optional for registration

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}