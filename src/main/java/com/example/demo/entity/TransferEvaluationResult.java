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

    public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
    }
    public Course getsourceCourse(){
        return sourceCourse;
    }
    public void setsourceCourse(Course sourceCourse){
        this.sourceCourse=sourceCourse;
    }
     public Course gettargetCourse(){
        return targetCourse;
    }
    public void settargetCourse(Course targetCourse){
        this.targetCourse=targetCourse;
    }
    public Double getoverlapPercentage(){
        return overlapPercentage;
    }
    public void setoverlapPercentage(Double overlapPercentage){
        this.overlapPercentage=overlapPercentage;
    }
    public Integer getcreditHourDifference(){
        return creditHourDifference;
    }
    public void setcreditHourDifference(Integer creditHourDifference){
        this.creditHourDifference=creditHourDifference;
    }
     public Boolean getisEligibleForTransfer(){
        return isEligibleForTransfer;
    }
    public void setisEligibleForTransfer(Boolean isEligibleForTransfer){
        this.isEligibleForTransfer=isEligibleForTransfer;
    }
     public Timestamp getEvaluatedAt(){
        return EvaluatedAt;
    }
    public void setEvaluatedAt(Timestamp evaluatedAt){
        this.evaluatedAt=evaluatedAt;
    }

     public String getnotes(){
        return id;
    }
    public void setnotes(String notes){
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
