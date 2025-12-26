package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseMapping;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseMappingRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMappingServiceImpl implements CourseMappingService {

    private final CourseMappingRepository mappingRepository;
    private final CourseRepository courseRepository;

    public CourseMappingServiceImpl(CourseMappingRepository mappingRepository, CourseRepository courseRepository) {
        this.mappingRepository = mappingRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseMapping createMapping(CourseMapping mapping) {
        Course source = courseRepository.findById(mapping.getSourceCourse().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Source Course", mapping.getSourceCourse().getId()));
        Course target = courseRepository.findById(mapping.getTargetCourse().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Target Course", mapping.getTargetCourse().getId()));

        List<CourseMapping> existing = mappingRepository.findBySourceCourseId(source.getId());
        boolean duplicate = existing.stream()
                .anyMatch(m -> m.getTargetCourse().getId().equals(target.getId()));

        if (duplicate) {
            throw new BadRequestException("Duplicate course mapping");
        }

        mapping.setSourceCourse(source);
        mapping.setTargetCourse(target);

        return mappingRepository.save(mapping);
    }

    @Override
    public List<CourseMapping> getMappingsBySourceCourseId(Long sourceCourseId) {
        return mappingRepository.findBySourceCourseId(sourceCourseId);
    }

    @Override
    public List<CourseMapping> getMappingsByTargetCourseId(Long targetCourseId) {
        return mappingRepository.findByTargetCourseId(targetCourseId);
    }
}
