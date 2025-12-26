package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository repo;
    private final CourseRepository courseRepo;

    public CourseContentTopicServiceImpl(CourseContentTopicRepository repo, CourseRepository courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic t) {
        if (t.getTopicName() == null || t.getTopicName().isBlank())
            throw new IllegalArgumentException("Topic name required");

        if (t.getWeightPercentage() == null || t.getWeightPercentage() < 0 || t.getWeightPercentage() > 100)
            throw new IllegalArgumentException("Weight must be 0-100");

        // Ensure course exists and assign
        Course course = courseRepo.findById(t.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        t.setCourse(course);

        return repo.save(t);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic t) {
        CourseContentTopic ex = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        ex.setTopicName(t.getTopicName());
        ex.setWeightPercentage(t.getWeightPercentage());

        if (t.getCourse() != null) {
            Course course = courseRepo.findById(t.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            ex.setCourse(course);
        }

        return repo.save(ex);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
    }

    @Override
    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
        courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return repo.findByCourseId(courseId);
    }
}
