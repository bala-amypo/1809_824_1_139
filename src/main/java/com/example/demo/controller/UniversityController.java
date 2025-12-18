package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;

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
        return atrs.getViewByID(id);
    }

    
    @GetMapping
    public List<University> getUniversities() {
        return atrs.getUniversity();
    }

    @PutMapping("/{id}/deactivate")
    public University deactivateUniversity(@PathVariable Long id) {
        return atrs.deactivateUniversity(id);
    }
}