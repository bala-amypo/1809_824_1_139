package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University createUniversity(University univ) {
        univ.setActive(true);   // assuming active field exists
        return universityRepository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University existing = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));

        existing.setName(univ.getName());
        existing.setLocation(univ.getLocation());
        existing.setActive(univ.isActive());

        return universityRepository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));

        university.setActive(false);
        universityRepository.save(university);
    }
}