package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService service;

    @PostMapping
    public University create(@RequestBody University u) {
        return service.createUniversity(u);
    }

    @GetMapping("/{id}")
    public University getById(@PathVariable Long id) {
        return service.getUniversityById(id);
    }

    @PutMapping("/{id}")
    public University update(@PathVariable Long id, @RequestBody University u) {
        return service.updateUniversity(id, u);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateUniversity(id);
    }

    @GetMapping
    public List<University> getAll() {
        return service.getAllUniversities();
    }
}
