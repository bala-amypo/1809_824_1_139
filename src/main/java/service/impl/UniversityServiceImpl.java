package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;

public class UniversityServiceImpl {

    private UniversityRepository repository;

    public University createUniversity(University u) {
        if (u.getName() == null || u.getName().isBlank())
            throw new IllegalArgumentException("Name required");
        if (repository.findByName(u.getName()).isPresent())
            throw new IllegalArgumentException("University exists");
        return repository.save(u);
    }

    public University updateUniversity(Long id, University u) {
        University e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        e.setName(u.getName());
        return repository.save(e);
    }

    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public void deactivateUniversity(Long id) {
        University u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        u.setActive(false);
        repository.save(u);
    }
}

