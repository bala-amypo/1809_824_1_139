package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.UniversityService;
import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository repo;

    public UniversityServiceImpl(UniversityRepository repo) {
        this.repo = repo;
    }

    @Override
    public University createUniversity(University university) {
        return repo.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {
        university.setId(id);
        return repo.save(university);
    }

    @Override
    public University getUniversityById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<University> getAllUniversities() {
        return repo.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        repo.deleteById(id);   
    }
}