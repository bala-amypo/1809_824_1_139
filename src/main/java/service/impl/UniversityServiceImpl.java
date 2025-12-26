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
    private UniversityRepository universityRepository;

    @Override
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));
    }
}
