package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

import java.util.List;

@Service
public class CourseImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // POST /
    @Override
    public Course createCourse(Course course) {
        course.setActive(true);   // default active
        return courseRepository.save(course);
    }

    // PUT /{id}
    @Override
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        existingCourse.courseName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setUniversity(course.getUniversityId());
        existingCourse.setActive(course.isActive());

        return courseRepository.save(existingCourse);
    }

    // GET /{id}
    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // GET /university/{universityId}
    @Override
    public List<Course> getCoursesByUniversityId(Long universityId) {
        return courseRepository.findByUniversityId(universityId);
    }

    // PUT /{id}/deactivate
    @Override
    public Course deactivateCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        course.setActive(false);
        return courseRepository.save(course);
    }
}