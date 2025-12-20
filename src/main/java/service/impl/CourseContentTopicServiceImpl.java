package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.CourseContentTopicService;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopic {

    private final CourseContentTopicRepository repo;

    public CourseContentTopicImpl(CourseContentTopicRepository repo) {
        this.repo = repo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return repo.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic university) {
        university.setId(id);
        return repo.save(university);
    }

    @Override
    public University getTopicById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<CourseContentTopic> getTopicsForCourse() {
        return repo.findAll();
    }

    
}