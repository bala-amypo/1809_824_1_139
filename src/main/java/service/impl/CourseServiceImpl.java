package com.example.demo.service.impls;

import com.example.demo.service.CourseService;
import com.example.demo.repository.CourseRepository;
import com.example.demo.entity.Course;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CourseImpls implements CourseService{
    
    @Autowired
    private CourseRepository atrr;

    @Override
    public Course logEvent(AuditTrailRecord record){
        return atrr.save(record);
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId){
        return atrr.findByCredentialId(credentialId);
    }

    @Override
    public List<AuditTrailRecord> getAllLogs(){
        return atrr.findAll();
    }
}