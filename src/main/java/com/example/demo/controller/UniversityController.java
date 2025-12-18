package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;

@RestController
public class UniversityController {
    @Autowired
    UniversityService atrs;

    @PostMapping("/University")
    public University addUniversity(@RequestBody AuditTrailRecord atr){
        return atrs.logEvent(atr);
    }

    @GetMapping("/")
    public int first(Long credentialId){
        return atrs.getLogs();
    }

    @GetMapping
    public List<AuditTrailRecord> second(){
        return atrs.getAllLogs();
    }
}