// package com.example.demo.controller;

// import com.example.demo.entity.Course;
// import com.example.demo.service.CourseService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/courses")
// public class CourseController {

//     @Autowired
//     private CourseService courseService;

//     @PostMapping
//     public ResponseEntity<Course> createCourse(@RequestBody Course course) {
//         Course created = courseService.createCourse(course);
//         return ResponseEntity.ok(created);
//     }

//     @GetMapping("/university/{universityId}")
//     public ResponseEntity<List<Course>> getCoursesByUniversity(@PathVariable Long universityId) {
//         List<Course> courses = courseService.getCoursesByUniversityId(universityId);
//         return ResponseEntity.ok(courses);
//     }
// }
