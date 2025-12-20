package com.example.demo.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 import com.example.demo.entity.CourseContentTopic;
 import java.util.List;
 @Repository
 public interface CourseContentTopicRepository extends JpaRepository<CourseContentTopic, Long> {
     

     }