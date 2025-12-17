package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class TransferEvaluationResult{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    public void setTargetCourse(Course targetCourse){
        this.targetCourse=targetCourse;
    }
    public Double getOverlapPercentage(){
        return overlapPercentage;
    }
    public void setOverlapPercentage(Double overlapPercentage){
        this.overlapPercentage=overlapPercentage;
    }
    public Integer getCreditHourDifference(){
        return creditHourDifference;
    }
    public void setCreditHourDifference(Integer creditHourDifference){
        this.creditHourDifference=creditHourDifference;
    }
     public Boolean getIsEligibleForTransfer(){
        return isEligibleForTransfer;
    }
    public void setIsEligibleForTransfer(Boolean isEligibleForTransfer){
        this.isEligibleForTransfer=isEligibleForTransfer;
    }
     public Timestamp getEvaluatedAt(){
        return evaluatedAt;
    }
    public void setEvaluatedAt(Timestamp evaluatedAt){
        this.evaluatedAt=evaluatedAt;
    }

     public String getNotes(){
        return id;
    }
    public void setNotes(String notes){
        this.notes=notes;
    }
    public TransferEvaluationResult(Long id,Course sourceCourse,Course targetCourse,Double overlapPercentage,Integer creditHourDifference,Boolean isEligibleTransfer,Timestamp evaluatedAt,String notes){
        this.id=id;
        this.sourceCourse=sourceCourse;
        this.targetCourse=targetCourse;
        this.overlapPercentage=overlapPercentage;
        this.creditHourDifference=creditHourDifference;
        this.isEligibleForTransfer=isEligibleForTransfer;
        this.evaluatedAt=evaluatedAt;
        this.notes=notes;
    }
    public TransferEvaluationResult(){

    }
    
}
