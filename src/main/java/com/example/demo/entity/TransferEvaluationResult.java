package com.example.demo.entity;
public class TransferEvaluationResult{
    private Long id;
    private Course sourceCourse;
    private Course targetCourse;
    private Double overlapPercentage;
    private Integer creditHourDifference;
    private Boolean isEligibleForTransfer;
    private Timestamp evaluatedAt;
    private String notes;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Course getSourceCourse(){
        return sourceCourse;
    }
    public void setSourceCourse(Course sourceCourse){
        this.sourceCourse=sourceCourse;
    }
     public Course getTargetCourse(){
        return targetCourse;
    }
    public void setTarget(Long id){
        this.id=id;
    }
    
}
