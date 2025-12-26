package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.service.CourseContentTopicService;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository repository;

    public CourseContentTopicServiceImpl(CourseContentTopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic t) {
        return repository.save(t);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic t) {
        CourseContentTopic existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with id " + id));
        existing.setName(t.getName());
        existing.setCourse(t.getCourse());
        // update other fields as needed
        return repository.save(existing);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with id " + id));
    }

    @Override
    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
