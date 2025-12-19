package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.CourseService;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Course createCourse(Course course) {
        return repo.save(course);
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