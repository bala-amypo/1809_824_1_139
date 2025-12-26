package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import java.util.Optional;

public class UniversityServiceImpl {

    private UniversityRepository repository;

    public University createUniversity(University u) {
        if (u.getName() == null || u.getName().isBlank())
            throw new IllegalArgumentException("Name required");

        repository.findByName(u.getName())
                .ifPresent(x -> { throw new IllegalArgumentException("exists"); });

        return repository.save(u);
    }

    public University updateUniversity(Long id, University u) {
        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        existing.setName(u.getName());
        return repository.save(existing);
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
