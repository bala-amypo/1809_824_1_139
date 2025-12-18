package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University createUniversity(University univ) {
        return universityRepository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        Optional<University> existingOpt = universityRepository.findById(id);
        if (existingOpt.isPresent()) {
            University existing = existingOpt.get();
            existing.setName(univ.getName());
            existing.setLocation(univ.getLocation());
            existing.setEstablishedYear(univ.getEstablishedYear());
            // Add any other fields of University entity here
            return universityRepository.save(existing);
        } else {
            throw new RuntimeException("University not found with id: " + id);
        }
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University existing = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
        existing.setActive(false); // Make sure University entity has an 'active' boolean field
        universityRepository.save(existing);
    }
}