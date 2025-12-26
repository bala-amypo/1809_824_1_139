package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.University;
import com.example.demo.service.impl.UniversityServiceImpl;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityServiceImpl service;

    public UniversityController(UniversityServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public University create(@RequestBody University u) {
        return service.createUniversity(u);
    }

    @PutMapping("/{id}")
    public University update(@PathVariable Long id, @RequestBody University u) {
        return service.updateUniversity(id, u);
    }

    @GetMapping("/{id}")
    public University get(@PathVariable Long id) {
        return service.getUniversityById(id);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateUniversity(id);
    }
}
