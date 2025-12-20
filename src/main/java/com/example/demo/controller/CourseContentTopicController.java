package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.CourseContentTopicService;

import java.util.List;

@RestController
public class CourseContentTopicController {

    @Autowired
    CourseContentTopicService uni;   

    @PostMapping("/")
    public CourseContentTopic addCourseContentTopic(@RequestBody CourseContentTopic topic) {
        return uni.createCourseContentTopic(topic);
    }

    @PutMapping("/{id}")
    public CourseContentTopic updatetopic(@PathVariable Long courseId,
                                       @Valid @RequestBody CourseContentTopic topic) {
        return uni.updateCourseContentTopic(id, topic); 
    }

    @GetMapping("/{id}")
    public CourseContentTopic gettopic(@PathVariable Long id) {
        return uni.getTopicById(id); 
    }
    @GetMapping("/course/{courseId}")
    public List<@GetMapping("/university/{universityId}")
    public List<Course> getCoursesByUniversityId(
            @PathVariable Long universityId) {
        return courseService.getCoursesByUniversityId(universityId);
    }> getCoursesByUniversityId(
            @PathVariable Long universityId) {
        return courseService.getCoursesByUniversityId(universityId);
    }

    
   
    
}