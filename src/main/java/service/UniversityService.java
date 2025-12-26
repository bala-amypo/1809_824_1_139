package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.University;

public interface UniversityService {
    University save(University university);
    List<University> getAll();
    University getById(Long id);
}
