package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double overlapPercentage;
    private Integer creditHourDifference;
    private Boolean isEligibleForTransfer;
    private String notes;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOverlapPercentage() {
        return overlapPercentage;
    }

    public void setOverlapPercentage(Double overlapPercentage) {
        this.overlapPercentage = overlapPercentage;
    }

    public Integer getCreditHourDifference() {
        return creditHourDifference;
    }

    public void setCreditHourDifference(Integer creditHourDifference) {
        this.creditHourDifference = creditHourDifference;
    }

    public Boolean getIsEligibleForTransfer() {
        return isEligibleForTransfer;
    }

    public void setIsEligibleForTransfer(Boolean isEligibleForTransfer) {
        this.isEligibleForTransfer = isEligibleForTransfer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    
    public TransferEvaluationResult(
            Double overlapPercentage,
            Integer creditHourDifference,
            Boolean isEligibleForTransfer,
            String notes) {

        this.overlapPercentage = overlapPercentage;
        this.creditHourDifference = creditHourDifference;
        this.isEligibleForTransfer = isEligibleForTransfer;
        this.notes = notes;
    }

    
    public TransferEvaluationResult() {
    }
}
