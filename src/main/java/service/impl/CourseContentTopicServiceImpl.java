// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;
// import com.example.demo.service.CourseContentTopicService;
// import com.example.demo.entity.CourseContentTopic;
// import com.example.demo.repository.CourseContentTopicRepository;

// import java.util.List;

// @Service
// public class CourseContentTopicServiceImpl implements CourseContentTopicService {

//     private final CourseContentTopicRepository repo;

//     public CourseContentTopicServiceImpl(CourseContentTopicRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public CourseContentTopic createTopic(CourseContentTopic topic) {
 //         return repo.save(topic);
//     }

//     @Override
//     public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
//         topic.setId(id);
//         return repo.save(topic);
//     }

//     @Override   
//     public CourseContentTopic getTopicById(Long id) {
//         return repo.findById(id).orElse(null);
//     }

//     @Override
//     public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
//         return repo.findByCourseId(courseId);
//     }
 // }
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class CourseContentTopicServiceImpl {

    CourseContentTopicRepository repo;
    CourseRepository courseRepo;

    public CourseContentTopic createTopic(CourseContentTopic t) {
        if (t.getTopicName() == null || t.getTopicName().isBlank())
            throw new IllegalArgumentException("Topic name");

        if (t.getWeightPercentage() < 0 || t.getWeightPercentage() > 100)
            throw new IllegalArgumentException("0-100");

        courseRepo.findById(t.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("not found"));

        return repo.save(t);
    }

    public CourseContentTopic updateTopic(Long id, CourseContentTopic t) {
        CourseContentTopic ex = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        return repo.save(ex);
    }

    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<CourseContentTopic> getTopicsForCourse(Long id) {
        courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        return repo.findByCourseId(id);
    }
}
