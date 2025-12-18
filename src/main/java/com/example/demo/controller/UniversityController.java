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

    @PostMapping("/")
    public University addUniversity( @Valid @RequestBody University atrs){
        return atrs.createUniversity(University);
    }
    @PutMapping("/{id}")
    public University updateUniversity(@PathVariable Long id,@Valid @RequestBody University university){
        return atrs.updateuniversity(id,university);
    }
    @GetMapping("/{id}")
    public University getUniversity(@PathVariable Long id){
        return atrs.getViewByID(id);
    }
    @GetMapping("/")
    public University getUniversity(Long University){
        return atrs.getUniversity();
    }
    @PutMapping("/{id}")
    public University updateUniversity(@PathVariable Long id,@Valid @RequestBody University university){
        return atrs.updateuniversity(id,university);
    }


   
}