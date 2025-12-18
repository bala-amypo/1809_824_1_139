package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CourseContentTopic;
public interface CourseRepository extends JpaRepository<Course,Long>{
    
}