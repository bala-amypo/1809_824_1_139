package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class CourseContentTopic{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Course course;
    private String topicName;
    private Double weightPercentage;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
     public Course getCourse(){
        return course;
    }
    public void setCourse(Course course){
        this.course=course;
    }
    public String getTopicName(){
        return topicName;
    }
    public void setTopicName(String topicName){
        this.topicName=topicName;
    }
    public Double getWeightPercentage(){
        return weightPercentage;
    }
    public void setWeightPercentage(Double weightPercentage){
        this.weightPercentage=weightPercentage;
    }
    
}
