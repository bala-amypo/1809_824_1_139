package com.example.demo.entity;
public class TransferRule{
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
    public void setSourceUniversity(University SourceUniversity){
        this.sourceUniversity=sourceUniversity;
    }
    public University getTargetUniversity(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }


    
}