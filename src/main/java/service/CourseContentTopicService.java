package com.example.demo.service;
import com.example.demo.entity.CourseContentTopic;
public interface CourseContentTopicService{
    CourseContentTopic createTopic(CourseContentTopic topic);
    List<CourseContentTopic>getTopicsForCourse(Long courseId);
    Course getTopicsForCourse(Long courseId);
    List<CourseContentTopic>getCoursesByUniversity(Long universityId);
    void updateTopic(Long id,CourseContentTopic topic);
}
   