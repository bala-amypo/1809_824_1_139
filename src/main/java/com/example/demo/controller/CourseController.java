package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Course;
import com.example.demo.service.impl.CourseServiceImpl;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseServiceImpl service;

    public CourseController(CourseServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Course create(@RequestBody Course c) {
        return service.createCourse(c);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course c) {
        return service.updateCourse(id, c);
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @GetMapping("/university/{uid}")
    public List<Course> getByUniversity(@PathVariable Long uid) {
        return service.getCoursesByUniversity(uid);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateCourse(id);
    }
}
