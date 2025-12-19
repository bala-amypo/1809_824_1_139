package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    // REQUIRED FIELD NAMES (DO NOT CHANGE)
    private final CourseRepository repo;
    private final UniversityRepository univRepo;

    public CourseServiceImpl(CourseRepository repo,
                             UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public Course createCourse(Course course) {

        if (course.getCreditHours() == null || course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("> 0");
        }

        Long universityId = course.getUniversity().getId();

        University university = univRepo.findById(universityId)
                .orElseThrow(() -> new RuntimeException("not found"));

        repo.findByUniversityldAndCourseCode(
                universityId,
                course.getCourseCode()
        ).ifPresent(c -> {
            throw new IllegalArgumentException("exists");
        });

        course.setUniversity(university);
        course.setActive(true);

        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {

        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (course.getCreditHours() != null && course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("> 0");
        }

        existing.setCourseCode(course.getCourseCode());
        existing.setCourseName(course.getCourseName());
        existing.setCreditHours(course.getCreditHours());
        existing.setDescription(course.getDescription());
        existing.setDepartment(course.getDepartment());

        return repo.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityld) {

        univRepo.findById(universityld)
                .orElseThrow(() -> new RuntimeException("not found"));

        return repo.findByUniversityldAndActiveTrue(universityld);
    }

    @Override
    public Course deactivateCourse(Long id) {

        Course course = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        course.setActive(false);
        return repo.save(course);
    }
}
