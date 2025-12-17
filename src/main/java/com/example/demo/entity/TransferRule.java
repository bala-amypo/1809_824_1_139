package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class TransferRule{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private University sourceUniversity;
    private University targetUniversity;
    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;
    private Boolean active;
    
    public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
    }
     public University getsourceUniversity(){
        return sourceUniversity;
    }
    public void setsourceUniversity(University sourceUniversity){
        this.sourceUniversity=sourceUniversity;
    }
    public University gettargetUniversity(){
        return targetUniversity;
    }
    public void settargetUniversity(University targetUniversity){
        this.targetUniversity=targetUniversity;
    }
    
    public Double getminimumOverlapPercentage(){
        return minimumOverlapPercentage;
    }
    public void setminimumOverlapPercentage(Double minimumOverlapPercentage){
        this.minimumOverlapPercentage=minimumOverlapPercentage;
    }
    public Integer getcreditHourTolerance(){
        return creditHourTolerance;
    }
    public void setcreditHourTolerance(Integer creditHourTolerance){
        this.creditHourTolerance=creditHourTolerance;
    }
     public Boolean getactive(){
        return active;
    }
    public void setactive(Boolean active){
        this.active=active;
    }
    public TransferRule(Long id,University sourceUniversity,University targetUniversity,Double minimumOverlapPercentage,Integer creditHourTolerance,Boolean active){
        this.id=id;
        this.sourceUniversity=sourceUniversity;
        this.targetUniversity=targetUniversity;
        this.minimumOverlapPercentage=minimumOverlapPercentage;
        this.creditHourTolerance=creditHourTolerance;
        this.active=active;
    }
    public TransferRule(){
        
    }



    
}