package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import java.util.Long;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;

import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    UniversityService uni;   

    @PostMapping("/")
    public University addUniversity(@RequestBody University university) {
        return uni.createUniversity(university);
    }

    @PutMapping("/{id}")
    public University updateuniversity(@PathVariable Long id,
                                       @Valid @RequestBody University university) {
        return uni.updateUniversity(id, university); 
    }

    @GetMapping("/{id}")
    public University getuniversity(@PathVariable Long id) {
        return uni.getUniversityById(id); 
    }

    @GetMapping("/")
    public List<University> getAllUniversities() {
        return uni.getAllUniversities();
    }
}