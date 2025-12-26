package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class CourseServiceImpl {

    private CourseRepository repo;
    private UniversityRepository univRepo;

    public Course createCourse(Course c) {
        if (c.getCreditHours() <= 0)
            throw new IllegalArgumentException("> 0");

        University u = univRepo.findById(c.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("not found"));

        repo.findByUniversityIdAndCourseCode(u.getId(), c.getCourseCode())
                .ifPresent(x -> { throw new IllegalArgumentException("duplicate"); });

        return repo.save(c);
    }

    public void deactivateCourse(Long id) {
        Course c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        c.setActive(false);
        repo.save(c);
    }

    public Course updateCourse(Long id, Course c) {
        Course ex = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        return repo.save(ex);
    }

    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Course> getCoursesByUniversity(Long uid) {
        return repo.findByUniversityIdAndActiveTrue(uid);
    }
}
