
package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class course{
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private University university;
    private String courseCode;
    private String courseName;
    private Integer creditHours;
    private String description;
    private String department;
    private Boolean active;
   

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
     public University getUniversity(){
        return university;
    }
    public void setUniversity(University university){
        this.university=university;
    }

    public String getCourseCode(){
        return courseCode;
    }
    public void setCourseCode(String courseCode){
        this.courseCode=courseCode;
    }
    public String getCourseName(){
        return courseName;
    }
    public void setCourseName(String courseName){
        this.courseName=courseName;
    }
    public Integer getCreditHours(){
        return creditHours;
    }
    public void setCreditHours(Integer creditHours){
        this.creditHours=creditHours;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department=department;
    }
    public Boolean getActive(){
        return active;
    }
    public Boolean setActive(String active){
        this.active=active;
    }
    public Course(Long id,University university,String courseCode,String courseName,Integer )


    
}