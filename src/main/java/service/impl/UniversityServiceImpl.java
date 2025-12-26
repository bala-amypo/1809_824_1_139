// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.service.UniversityService;
// import com.example.demo.entity.University;
// import com.example.demo.repository.UniversityRepository;

// import java.util.List;

// @Service
// public class UniversityServiceImpl implements UniversityService {

//     private final UniversityRepository repo;

//     public UniversityServiceImpl(UniversityRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public University createUniversity(University university) {
//         return repo.save(university);
//     }

//     @Override
//     public University updateUniversity(Long id, University university) {
//         university.setId(id);
//         return repo.save(university);
//     }

//     @Override
//     public University getUniversityById(Long id) {
//         return repo.findById(id).orElse(null);
//     }

//     @Override
//     public List<University> getAllUniversities() {
//         return repo.findAll();
//     }

//     @Override
//     public void deactivateUniversity(Long id) {
//         repo.deleteById(id);   
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class UniversityServiceImpl {

    UniversityRepository repository;

    public University createUniversity(University u) {
        if (u.getName() == null || u.getName().isBlank())
            throw new IllegalArgumentException("Name required");

        if (repository.findByName(u.getName()).isPresent())
            throw new IllegalArgumentException("University exists");

        return repository.save(u);
    }

    public University updateUniversity(Long id, University u) {
        University ex = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        ex.setName(u.getName());
        return repository.save(ex);
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
