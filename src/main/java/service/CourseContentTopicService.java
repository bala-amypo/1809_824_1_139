package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;

import java.util.List;

public interface CourseContentTopicService {
    CourseContentTopic createTopic(CourseContentTopic topic);
    List<CourseContentTopic> getTopicsByCourseId(Long courseId);
}
