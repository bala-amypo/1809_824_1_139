
 package com.example.demo.service;

import com.example.demo.entity.Course;
import java.util.List;

public interface CourseService {
    Course createCourse(Course c);
    Course updateCourse(Long id, Course c);
    Course getCourseById(Long id);
    void deactivateCourse(Long id);
    List<Course> getCoursesByUniversity(Long universityId);
}
