package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService atrs;

    @PostMapping
    public University addUniversity(@Valid @RequestBody University university) {
        return atrs.createUniversity(university);
    }

    @PutMapping("/{id}")
    public University updateUniversity(@PathVariable Long id,
                                       @Valid @RequestBody University university) {
        return atrs.updateUniversity(id, university);
    }

    @GetMapping("/{id}")
    public University getUniversity(@PathVariable Long id) {
        return atrs.getUniversityById(id);
    }

    @GetMapping
    public List<University> getUniversities() {
        return atrs.getAllUniversities();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateUniversity(@PathVariable Long id) {
        atrs.deactivateUniversity(id);
    }
}