package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;
    private final UniversityRepository univRepo;

    public CourseServiceImpl(CourseRepository repo, UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public Course createCourse(Course c) {
        if (c.getCreditHours() == null || c.getCreditHours() <= 0)
            throw new IllegalArgumentException("Credit hours must be > 0");

        University u = univRepo.findById(c.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found"));

        if (repo.findByUniversityIdAndCourseCode(u.getId(), c.getCourseCode()).isPresent())
            throw new IllegalArgumentException("Course already exists");

        c.setUniversity(u);
        c.setActive(true);
        return repo.save(c);
    }

    @Override
    public Course updateCourse(Long id, Course c) {
        Course ex = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        ex.setCourseCode(c.getCourseCode());
        ex.setCourseName(c.getCourseName());
        ex.setCreditHours(c.getCreditHours());
        ex.setDescription(c.getDescription());
        ex.setDepartment(c.getDepartment());
        ex.setActive(c.getActive());
        return repo.save(ex);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public void deactivateCourse(Long id) {
        Course c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        c.setActive(false);
        repo.save(c);
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }
}
