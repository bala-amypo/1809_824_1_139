package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService uni;

    // POST /universities
    @PostMapping
    public University addUniversity(@Valid @RequestBody University university) {
        return uni.createUniversity(university);
    }

    // PUT /universities/{id}
    @PutMapping("/{id}")
    public University updateUniversity(@PathVariable Long id,
                                       @Valid @RequestBody University university) {
        return uni.updateUniversity(id, university);
    }

    // GET /universities/{id}
    @GetMapping("/{id}")
    public University getUniversity(@PathVariable Long id) {
        return uni.getUniversityById(id);
    }

    // GET /universities
    @GetMapping
    public List<University> getAllUniversities() {
        return uni.getAllUniversities();
    }
}