package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CourseContentTopic;

public interface CourseContentTopicService {
    CourseContentTopic save(CourseContentTopic topic, Long courseId);
    List<CourseContentTopic> getByCourse(Long courseId);
}
