package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities/{universityId}/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

   
    @PostMapping
    public Course createCourse(
            @PathVariable Long universityId,
            @RequestBody Course course) {
        return courseService.createCourse(universityId, course);
    }

    
    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long universityId,
            @PathVariable Long id,
            @RequestBody Course course) {
        return courseService.updateCourse(universityId, id, course);
    }

    
    @GetMapping("/{id}")
    public Course getCourseById(
            @PathVariable Long universityId,
            @PathVariable Long id) {
        return courseService.getCourseById(universityId, id);
    }

   
    @GetMapping
    public List<Course> getAllCourses(
            @PathVariable Long universityId) {
        return courseService.getAllCoursesByUniversity(universityId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateCourse(
            @PathVariable Long universityId,
            @PathVariable Long id) {
        courseService.deactivateCourse(universityId, id);
    }
}