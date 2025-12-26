package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Course;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByUniversityIdAndCourseCode(Long universityId, String courseCode);
    List<Course> findByUniversityIdAndActiveTrue(Long universityId);
}
