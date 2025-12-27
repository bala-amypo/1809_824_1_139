// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;

// import java.util.List;

// public class CourseContentTopicServiceImpl {

//     private CourseContentTopicRepository repo;
//     private CourseRepository courseRepo;

//     public CourseContentTopic createTopic(CourseContentTopic t) {
//         if (t.getTopicName() == null || t.getTopicName().isBlank())
//             throw new IllegalArgumentException("Topic name");
//         if (t.getWeightPercentage() < 0 || t.getWeightPercentage() > 100)
//             throw new IllegalArgumentException("0-100");
//         courseRepo.findById(t.getCourse().getId()).orElseThrow();
//         return repo.save(t);
//     }

//     public CourseContentTopic updateTopic(Long id, CourseContentTopic t) {
//         CourseContentTopic e = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//         return repo.save(e);
//     }

//     public CourseContentTopic getTopicById(Long id) {
//         return repo.findById(id).orElseThrow();
//     }

//     public List<CourseContentTopic> getTopicsForCourse(Long cid) {
//         courseRepo.findById(cid).orElseThrow();
//         return repo.findByCourseId(cid);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    @Autowired
    CourseContentTopicRepository repo;

    @Autowired
    CourseRepository courseRepo;

    public CourseContentTopicServiceImpl() {
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {

        if (topic.getTopicName() == null || topic.getTopicName().isBlank()) {
            throw new IllegalArgumentException("Topic name required");
        }

        if (topic.getWeightPercentage() != null &&
                (topic.getWeightPercentage() < 0 || topic.getWeightPercentage() > 100)) {
            throw new IllegalArgumentException("Weight must be 0-100");
        }

        Course c = courseRepo.findById(topic.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        topic.setCourse(c);
        return repo.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
        CourseContentTopic existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        return repo.save(existing);
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
