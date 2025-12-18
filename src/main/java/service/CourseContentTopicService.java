package com.example.demo.service;
import com.example.demo.entity.CourseContentTopic;
public interface CourseContentTopicService{
    Course createTopic(CourseContentTopic topic);
    Course getTopicsForCourse(Long courseId);
    List<CourseContentTopic>getCoursesByUniversity(Long universityId);
    updateTopic(Long id,CourseContentTopic topic);
}
   