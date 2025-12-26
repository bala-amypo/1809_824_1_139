package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;
import java.util.List;

public interface CourseContentTopicService {
    CourseContentTopic createTopic(CourseContentTopic t);
    CourseContentTopic updateTopic(Long id, CourseContentTopic t);
    CourseContentTopic getTopicById(Long id);
    List<CourseContentTopic> getTopicsForCourse(Long courseId);
}
