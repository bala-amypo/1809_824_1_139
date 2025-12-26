package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.util.List;

public class CourseServiceImpl {

    private CourseRepository repo;
    private UniversityRepository univRepo;

    public Course createCourse(Course c) {
        if (c.getCreditHours() <= 0)
            throw new IllegalArgumentException("> 0");
        Long uid = c.getUniversity().getId();
        univRepo.findById(uid).orElseThrow();
        if (repo.findByUniversityIdAndCourseCode(uid, c.getCourseCode()).isPresent())
            throw new IllegalArgumentException("Duplicate");
        return repo.save(c);
    }

    public void deactivateCourse(Long id) {
        Course c = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        c.setActive(false);
        repo.save(c);
    }

    public Course updateCourse(Long id, Course c) {
        Course e = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        return repo.save(e);
    }

    public Course getCourseById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Course> getCoursesByUniversity(Long uid) {
        return repo.findByUniversityIdAndActiveTrue(uid);
    }
}
