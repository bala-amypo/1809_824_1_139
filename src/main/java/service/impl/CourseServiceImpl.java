// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;

// import java.util.List;

// public class CourseServiceImpl {

//     private CourseRepository repo;
//     private UniversityRepository univRepo;

//     public Course createCourse(Course c) {
//         if (c.getCreditHours() <= 0)
//             throw new IllegalArgumentException("> 0");
//         Long uid = c.getUniversity().getId();
//         univRepo.findById(uid).orElseThrow();
//         if (repo.findByUniversityIdAndCourseCode(uid, c.getCourseCode()).isPresent())
//             throw new IllegalArgumentException("Duplicate");
//         return repo.save(c);
//     }

//     public void deactivateCourse(Long id) {
//         Course c = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//         c.setActive(false);
//         repo.save(c);
//     }

//     public Course updateCourse(Long id, Course c) {
//         Course e = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//         return repo.save(e);
//     }

//     public Course getCourseById(Long id) {
//         return repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//     }

//     public List<Course> getCoursesByUniversity(Long uid) {
//         return repo.findByUniversityIdAndActiveTrue(uid);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository repo;

    @Autowired
    UniversityRepository univRepo;

    public CourseServiceImpl() {
    }

    @Override
    public Course createCourse(Course course) {

        if (course.getCreditHours() == null || course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("Credit hours must be > 0");
        }

        University u = univRepo.findById(course.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found"));

        repo.findByUniversityIdAndCourseCode(u.getId(), course.getCourseCode())
                .ifPresent(c -> { throw new IllegalArgumentException("Duplicate course"); });

        course.setUniversity(u);
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return repo.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public void deactivateCourse(Long id) {
        Course c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        c.setActive(false);
        repo.save(c);
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }
}
