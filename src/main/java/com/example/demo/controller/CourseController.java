package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService uni;   

    // POST /
    @PostMapping("/")
    public Course addCourse(@RequestBody Course course) {
        return uni.createCourse(course);
    }

    // PUT /{id}
    @PutMapping("/{id}")
    public Course updatecourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course) {
        return uni.updateCourse(id, course); 
    }

    // GET /{id}
    @GetMapping("/{id}")
    public Course getcourse(@PathVariable Long id) {
        return uni.getCourseById(id); 
    }

    // GET /
    @GetMapping("/")
    public List<Course> getAllCourses() {
        return uni.getAllCourses();
    }

    // GET /university/{universityId}
    @GetMapping("/university/{universityId}")
    public List<Course> getCourseByUniversityId(@PathVariable Long universityId) {
        return uni.getCourseByUniversityId(universityId);
    }

    // PUT /{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public void deactivateCourse(@PathVariable Long id) {
        uni.deactivateCourse(id);
    }
}