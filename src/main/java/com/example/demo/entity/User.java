package com.example.demo.entity;
@Entity
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @column(unique=true)
    private String email;
    private String password;
    private
    
}