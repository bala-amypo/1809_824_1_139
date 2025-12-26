package com.example.demo.service;

import com.example.demo.entity.University;

public interface UniversityService {
    University createUniversity(University university);
    University getById(Long id);
}
