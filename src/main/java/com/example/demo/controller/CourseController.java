package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import java.util.List;
import java.lang.Long;

@RestController
@RequestMapping("/api/courses")   
@Tag(name = "Courses")            
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course addCourse(@Valid @RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/university/{universityId}")
    public List<Course> getCoursesByUniversityId(
            @PathVariable Long universityId) {
        return courseService.getCoursesByUniversityId(universityId);
    }

    @PutMapping("/{id}/deactivate")
    public Course deactivateCourse(@PathVariable Long id) {
        return courseService.deactivateCourse(id);
    }
}
