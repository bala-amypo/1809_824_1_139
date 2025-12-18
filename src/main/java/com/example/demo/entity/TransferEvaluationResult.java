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
    private Double overlapPercentage;
    private Integer creditHourDifference;
    private Boolean isEligibleForTransfer;
    private String notes;

    public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
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
    

     public String getnotes(){
        return notes;
    }
    public void setnotes(String notes){
        this.notes=notes;
    }

    public TransferEvaluationResult(Long id,Double overlapPercentage,Integer creditHourDifference,Boolean isEligibleForTransfer,String notes){
        this.id=id;
        
        this.overlapPercentage=overlapPercentage;
        this.creditHourDifference=creditHourDifference;
        this.isEligibleForTransfer=isEligibleForTransfer;
        
        this.notes=notes;
    }
    public TransferEvaluationResult(){

    }
    }
    
    
    
    

