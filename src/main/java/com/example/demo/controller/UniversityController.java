package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @PostMapping
    public University create(@Valid @RequestBody University university) {
        return service.createUniversity(university);
    }

    @GetMapping("/{id}")
    public University getById(@PathVariable Long id) {
        return service.getUniversityById(id);
    }

    @PutMapping("/{id}")
    public University update(@PathVariable Long id,
                             @Valid @RequestBody University university) {
        return service.updateUniversity(id, university);
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
