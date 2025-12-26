package com.example.demo.service;

import com.example.demo.entity.University;

import java.util.List;

public interface UniversityService {
    University createUniversity(University university);
    List<University> getAllUniversities();
    University getUniversityById(Long id);
}
