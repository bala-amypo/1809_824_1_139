package com.example.demo.service;

import com.example.demo.entity.Course;

public interface CourseService {
    Course createCourse(Course course);
    Course getById(Long id);
    Course findByCode(String code);
}
