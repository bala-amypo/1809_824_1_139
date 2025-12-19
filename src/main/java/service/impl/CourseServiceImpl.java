package com.example.demo.service.impls;

import com.example.demo.service.CourseService;
import com.example.demo.repository.CourseRepository;
import com.example.demo.entity.Course;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    
    @Autowired
    private CourseRepository atrr;

    @Override
    public Course createCourse(Course course){
        return atrr.save(course);
    }
    @Override
public Course updateCourse(Long id, Course course) {

    Course existingCourse = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

    existingCourse.setName(course.getName());
    existingCourse.setUniversityId(course.getUniversityId());
    existingCourse.setActive(course.isActive());
   

    return atrr.save(existingCourse);
}

   
    @Override
    public Course getCourseById(Long id){
        return atrr.findById(id);
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId){
        return atrr.findByUniversityId( universityId);
    }
    @Override
public Course deactivateCourse(Long id) {

    Course course =atrr.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

    course.setActive(false);

    return atrr.save(course);
}
}