package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.service.CourseService;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

import java.lang.Long;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {   // 🔹 changed: interface → class

    private final CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Course createCourse(Course course) {
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        course.setId(id);
        return repo.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id).orElse(null);
    }

   
    @Override
    public List<Course> getCoursesByUniversityId(Long universityId) {
        return repo.findAll();
    }

    @Override
    public Course deactivateCourse(Long id) {
        Course course = repo.findById(id).orElse(null);
        if (course != null) {
            repo.deleteById(id);   // keeping your original behavior
        }
        return course;            // 🔹 return Course
    }
}
