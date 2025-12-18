package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
@RestController
public class UniversityController{
    @Autowired
    University uni;
    @PostMapping("/")
    public University addUniversity(@RequestBody University university){
    return uni.createUniversity(university);
    }
    @PutMapping("/{id}")
    public University update

}