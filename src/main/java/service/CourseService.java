package com.example.demo.service;
import com.example.demo.entity.Course;
public interface CourseService{
    createCourse(Course course);
    updateCourse(Long id,Course course);
    getCourseById(Long id);
    getCoursesByUniversity(Long universityId);
    void deactivateCourse(Long id);
}