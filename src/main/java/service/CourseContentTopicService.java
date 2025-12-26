package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CourseContentTopic;

public interface CourseContentTopicService {

    CourseContentTopic createTopic(CourseContentTopic t);

    CourseContentTopic updateTopic(Long id, CourseContentTopic t);

    CourseContentTopic getTopicById(Long id);

    List<CourseContentTopic> getTopicsForCourse(Long courseId);
}
