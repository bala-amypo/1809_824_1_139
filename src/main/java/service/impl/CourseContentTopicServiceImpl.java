package com.example.demo.service.impl;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    @Autowired
    private CourseContentTopicRepository topicRepository;

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public List<CourseContentTopic> getTopicsByCourseId(Long courseId) {
        return topicRepository.findByCourseId(courseId);
    }
}
