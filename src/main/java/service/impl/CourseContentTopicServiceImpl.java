package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.CourseContentTopicService;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;

import java.util.List;
import java.lang.Long;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository repo;

    // ✅ constructor name fixed + no return
    public CourseContentTopicServiceImpl(CourseContentTopicRepository repo) {
        this.repo = repo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return repo.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
        topic.setId(id);
        return repo.save(topic);
    }

    // ✅ return type fixed
    @Override
    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ✅ method signature fixed (added courseId)
    @Override
    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
        return repo.findAll(courseId);
    }
}
