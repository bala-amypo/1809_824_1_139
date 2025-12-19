package com.example.demo.service;
import com.example.demo.entity.Course;
public interface CourseService{
    Course createCourse(Course course);
    Course updateCourse(Long id,Course course);
    Course getCourseById(Long id);
    Course getCoursesByUniversity(Long universityId);
    Course deactivateCourse(Long id);
}