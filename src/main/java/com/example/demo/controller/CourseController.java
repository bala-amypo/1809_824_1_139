package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseService uni;   

    @PostMapping("/")
    public Course addCourse(@RequestBody Course course) {
        return uni.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updatecourse(@PathVariable Long id,
                                       @Valid @RequestBody Course course) {
        return uni.updateCourse(id,course); 
    }

    @GetMapping("/{id}")
    public Course getcourse(@PathVariable Long id) {
        return uni.getCourseById(id); 
    }
    @GetMapping("/university/{universityId}")
    public  List<Course>getCourseUniversityId(@PathVariable Long universityId){
    return uni.getCourseByUniversityId(universityId);

    }
    @PutMapping("/{id}/deactivate")
    public Course deactivateCourse(@PathVariable Long id){
    return uni.deactivateCourse(id);
    }
}