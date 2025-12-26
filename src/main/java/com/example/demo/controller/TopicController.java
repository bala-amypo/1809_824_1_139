package com.example.demo.controller;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.impl.CourseContentTopicServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final CourseContentTopicServiceImpl service =
            new CourseContentTopicServiceImpl();

    @PostMapping
    public CourseContentTopic create(@RequestBody CourseContentTopic t) {
        return service.createTopic(t);
    }

    @PutMapping("/{id}")
    public CourseContentTopic update(@PathVariable Long id, @RequestBody CourseContentTopic t) {
        return service.updateTopic(id, t);
    }

    @GetMapping("/{id}")
    public CourseContentTopic get(@PathVariable Long id) {
        return service.getTopicById(id);
    }

    @GetMapping("/course/{cid}")
    public List<CourseContentTopic> byCourse(@PathVariable Long cid) {
        return service.getTopicsForCourse(cid);
    }
}
