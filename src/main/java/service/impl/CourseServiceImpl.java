package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        if (course.getCredits() <= 0) {
            throw new BadRequestException("Course credits must be greater than 0");
        }

        courseRepository.findByCodeIgnoreCase(course.getCode())
                .ifPresent(c -> { throw new BadRequestException("Course code already exists"); });

        return courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    @Override
    public Course findByCode(String code) {
        return courseRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course with code " + code));
    }
}
