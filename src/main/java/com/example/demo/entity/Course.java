package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Course{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String courseName;
    private Integer creditHours;
    private String description;
    private String department;
    private Boolean active;
   

    public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
    }

    public String getcourseCode(){
        return courseCode;
    }
    public void setcourseCode(String courseCode){
        this.courseCode=courseCode;
    }
    public String getcourseName(){
        return courseName;
    }
    public void setcourseName(String courseName){
        this.courseName=courseName;
    }
    public Integer getcreditHours(){
        return creditHours;
    }
    public void setcreditHours(Integer creditHours){
        this.creditHours=creditHours;
    }
    public String getdescription(){
        return description;
    }
    public void setdescription(String description){
        this.description=description;
    }
    public String getdepartment(){
        return department;
    }
    public void setdepartment(String department){
        this.department=department;
    }
    public Boolean getactive(){
        return active;
    }
    public void setactive(Boolean active){
        this.active=active;
    }
    public Course(Long id,String courseCode,String courseName,Integer creditHours,String description,String department,Boolean active){
        this.id=id;
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.creditHours=creditHours;
        this.description=description;
        this.department=department;
        this.active=active;

    }
public Course(){   
}
}