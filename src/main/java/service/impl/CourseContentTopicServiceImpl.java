package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.CourseContentTopicService;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopic {

    private final CourseContentTopicRepository repo;

    public CourseContentTopicImpl(CourseContentTopicRepository repo) {
        this.repo = repo;
    }

    @Override
    public University createUniversity(University university) {
        return repo.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {
        university.setId(id);
        return repo.save(university);
    }

    @Override
    public University getUniversityById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<University> getAllUniversities() {
        return repo.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        repo.deleteById(id);   
    }
}