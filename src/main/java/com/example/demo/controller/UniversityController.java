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

    @PostMapping("/{}")
    public University addUniversity(@Valid @RequestBody University atr){
        return atrs.createUniversity(atr);
    }
    @PutMapping("/{id}")
    public University updateUniversity(@PathVariable Long id,@Valid @RequestBody University university){
        return atrs.updateuniversity(id,university);
    }
    @GetMapping("/{id}")
    public University getUniversity(@PathVariable Long id){
        return atrs.getViewByID(id);
    }
    @GetMapping("/University")
    public University addUniversity(@RequestBody University atr){
        return atrs.getUniversity(atr);
    }


   
}