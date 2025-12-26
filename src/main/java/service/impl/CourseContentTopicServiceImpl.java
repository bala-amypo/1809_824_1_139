package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.util.List;

public class CourseContentTopicServiceImpl {

    private CourseContentTopicRepository repo;
    private CourseRepository courseRepo;

    public CourseContentTopic createTopic(CourseContentTopic t) {
        if (t.getTopicName() == null || t.getTopicName().isBlank())
            throw new IllegalArgumentException("Topic name");
        if (t.getWeightPercentage() < 0 || t.getWeightPercentage() > 100)
            throw new IllegalArgumentException("0-100");
        courseRepo.findById(t.getCourse().getId()).orElseThrow();
        return repo.save(t);
    }

    public CourseContentTopic updateTopic(Long id, CourseContentTopic t) {
        CourseContentTopic e = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        return repo.save(e);
    }

    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<CourseContentTopic> getTopicsForCourse(Long cid) {
        courseRepo.findById(cid).orElseThrow();
        return repo.findByCourseId(cid);
    }
}
