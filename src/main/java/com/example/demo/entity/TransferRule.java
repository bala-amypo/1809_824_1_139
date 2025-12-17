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
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
     public University getSourceUniversity(){
        return sourceUniversity;
    }
    public void setSourceUniversity(University sourceUniversity){
        this.sourceUniversity=sourceUniversity;
    }
    public University getTargetUniversity(){
        return targetUniversity;
    }
    public void setTargetUniversity(University targetUniversity){
        this.targetUniversity=targetUniversity;
    }
    
    public Double getMinimumOverlapPercentage(){
        return minimumOverlapPercentage;
    }
    public void setMinimumOverlapPercentage(Double minimumOverlapPercentage){
        this.minimumOverlapPercentage=minimumOverlapPercentage;
    }
    public Integer getCreditHourTolerance(){
        return creditHourTolerance;
    }
    public void setCreditHourTolerance(Integer creditHourTolerance){
        this.creditHourTolerance=creditHourTolerance;
    }
     public Boolean getActive(){
        return id;
    }
    public void setActive(Boolean active){
        this.active=active;
    }
    public TransferRule(Long id,University sourceUniversity,University targetUniversity,Double minimumOverlapPercentage,Integer creditHourTolerance,Boolean active)



    
}