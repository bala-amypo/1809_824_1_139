// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;

// import com.example.demo.service.CourseService;
// import com.example.demo.entity.Course;
// import com.example.demo.repository.CourseRepository;

// import java.lang.Long;
// import java.util.List;

// @Service
// public class CourseServiceImpl implements CourseService {   // 🔹 changed: interface → class

//     private final CourseRepository repo;

//     public CourseServiceImpl(CourseRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public Course createCourse(Course course) {
 //         return repo.save(course);
//     }

//     @Override
//     public Course updateCourse(Long id, Course course) {
//         course.setId(id);
//         return repo.save(course);
//     }

//     @Override
//     public Course getCourseById(Long id) {
//         return repo.findById(id).orElse(null);
//     }

   
//     @Override
//     public List<Course> getCoursesByUniversityId(Long universityId) {
//         return repo.findAll();
//     }

//     @Override
//     public Course deactivateCourse(Long id) {
//         Course course = repo.findById(id).orElse(null);
//         if (course != null) {
//             repo.deleteById(id);   
//         }
//         return course;            
//     }
 // }
 package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class CourseServiceImpl {

    CourseRepository repo;
    UniversityRepository univRepo;

    public Course createCourse(Course c) {
        if (c.getCreditHours() <= 0)
            throw new IllegalArgumentException("> 0");

        University u = univRepo.findById(c.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("not found"));

        if (repo.findByUniversityIdAndCourseCode(u.getId(), c.getCourseCode()).isPresent())
            throw new IllegalArgumentException("duplicate");

        return repo.save(c);
    }

    public Course updateCourse(Long id, Course c) {
        Course ex = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        return repo.save(ex);
    }

    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public void deactivateCourse(Long id) {
        Course c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        c.setActive(false);
        repo.save(c);
    }

    public List<Course> getCoursesByUniversity(Long id) {
        return repo.findByUniversityIdAndActiveTrue(id);
    }
}

