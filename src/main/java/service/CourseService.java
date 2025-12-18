package com.example.demo.service;
import com.example.demo.entity.Course;
public interface CourseService{
    Course createCourse(Course course);
    void updateCourse(Long id,Course course);
    Course getCourseById(Long id);
    Course getCoursesByUniversity(Long universityId);
    void deactivateCourse(Long id);
}