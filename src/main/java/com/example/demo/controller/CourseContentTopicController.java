package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.CourseContentTopicService;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {

    @Autowired
    private CourseContentTopicService service;

    @PostMapping("/")
    public CourseContentTopic createTopic(@Valid @RequestBody CourseContentTopic topic) {
        return service.createTopic(topic);
    }

    @PutMapping("/{id}")
    public CourseContentTopic updateTopic(
            @PathVariable Long id,
            @Valid @RequestBody CourseContentTopic topic) {
        return service.updateTopic(id, topic);
    }

    @GetMapping("/{id}")
    public CourseContentTopic getTopicById(@PathVariable Long id) {
        return service.getTopicById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseContentTopic> getTopicsForCourse(@PathVariable Long courseId) {
        return service.getTopicsForCourse(courseId);
    }
}
