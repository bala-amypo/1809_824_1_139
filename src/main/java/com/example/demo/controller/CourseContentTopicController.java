// package com.example.demo.controller;

// import com.example.demo.entity.CourseContentTopic;
// import com.example.demo.service.CourseContentTopicService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/topics")
// public class CourseContentTopicController {

//     @Autowired
//     private CourseContentTopicService topicService;

//     @PostMapping
//     public ResponseEntity<CourseContentTopic> createTopic(@RequestBody CourseContentTopic topic) {
//         CourseContentTopic created = topicService.createTopic(topic);
//         return ResponseEntity.ok(created);
//     }

//     @GetMapping("/course/{courseId}")
//     public ResponseEntity<List<CourseContentTopic>> getTopicsByCourse(@PathVariable Long courseId) {
//         List<CourseContentTopic> topics = topicService.getTopicsByCourseId(courseId);
//         return ResponseEntity.ok(topics);
//     }
// }
