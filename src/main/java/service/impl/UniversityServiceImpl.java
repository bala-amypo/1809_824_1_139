package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository repository;

    @Override
    public University createUniversity(University u) {

        if (u == null || u.getName() == null || u.getName().isBlank())
            throw new IllegalArgumentException("Name required");

        if (repository.findByName(u.getName()).isPresent())
            throw new IllegalArgumentException("University already exists");

        u.setActive(true);
        return repository.save(u);
    }

    @Override
    public University updateUniversity(Long id, University u) {

        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));

        if (u.getName() != null && !u.getName().isBlank())
            existing.setName(u.getName());

        return repository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {

        University u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));

        u.setActive(false);
        repository.save(u);
    }
}
