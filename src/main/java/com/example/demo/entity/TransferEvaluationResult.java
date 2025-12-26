package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferEvaluationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sourceCourseId;
    private Long targetCourseId;
    private Double overlapPercentage;
    private Boolean isEligibleForTransfer;
    private String notes;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSourceCourseId() { return sourceCourseId; }
    public void setSourceCourseId(Long id) { this.sourceCourseId = id; }
    public Long getTargetCourseId() { return targetCourseId; }
    public void setTargetCourseId(Long id) { this.targetCourseId = id; }
    public Double getOverlapPercentage() { return overlapPercentage; }
    public void setOverlapPercentage(Double op) { this.overlapPercentage = op; }
    public Boolean getIsEligibleForTransfer() { return isEligibleForTransfer; }
    public void setIsEligibleForTransfer(Boolean eligible) { this.isEligibleForTransfer = eligible; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}