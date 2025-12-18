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
    
    private String topicName;
    private Double weightPercentage;

    public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
    }
    
    public String gettopicName(){
        return topicName;
    }
    public void settopicName(String topicName){
        this.topicName=topicName;
    }
    public Double getweightPercentage(){
        return weightPercentage;
    }
    public void setweightPercentage(Double weightPercentage){
        this.weightPercentage=weightPercentage;
    }
    public CourseContentTopic(Long id,String topicName,Double weightPercentage){
        this.id=id;
        this.topicName=topicName;
        this.weightPercentage=weightPercentage;
    }
    public CourseContentTopic(){
        
    }

    
}
