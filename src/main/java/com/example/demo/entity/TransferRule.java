package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class TransferRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_university_id", nullable = false)
    private University sourceUniversity;

    @ManyToOne
    @JoinColumn(name = "target_university_id", nullable = false)
    private University targetUniversity;

    private String courseCode;
    private Double creditTransferPercentage;
    private Boolean active;

    public TransferRule() {}

    public TransferRule(Long id, University sourceUniversity, University targetUniversity,
                        String courseCode, Double creditTransferPercentage, Boolean active) {
        this.id = id;
        this.sourceUniversity = sourceUniversity;
        this.targetUniversity = targetUniversity;
        this.courseCode = courseCode;
        this.creditTransferPercentage = creditTransferPercentage;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public University getSourceUniversity() {
        return sourceUniversity;
    }

    public void setSourceUniversity(University sourceUniversity) {
        this.sourceUniversity = sourceUniversity;
    }

    public University getTargetUniversity() {
        return targetUniversity;
    }

    public void setTargetUniversity(University targetUniversity) {
        this.targetUniversity = targetUniversity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Double getCreditTransferPercentage() {
        return creditTransferPercentage;
    }

    public void setCreditTransferPercentage(Double creditTransferPercentage) {
        this.creditTransferPercentage = creditTransferPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
