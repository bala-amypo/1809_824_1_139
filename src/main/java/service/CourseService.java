package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Course;

public interface CourseService {
    Course save(Course course, Long universityId);
    List<Course> getByUniversity(Long universityId);
}
