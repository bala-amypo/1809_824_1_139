package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository repository;

    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University u) {
        if (u.getName() == null || u.getName().isBlank())
            throw new IllegalArgumentException("Name required");

        if (repository.findByName(u.getName()).isPresent())
            throw new IllegalArgumentException("University exists");

        return repository.save(u);
    }

    @Override
    public University updateUniversity(Long id, University u) {
        University ex = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("University not found"));

        ex.setName(u.getName());
        ex.setAccreditationLevel(u.getAccreditationLevel());
        ex.setCountry(u.getCountry());
        ex.setActive(u.getActive());
        return repository.save(ex);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("University not found"));
    }

    @Override
    public void deactivateUniversity(Long id) {
        University u = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("University not found"));
        u.setActive(false);
        repository.save(u);
    }
}
