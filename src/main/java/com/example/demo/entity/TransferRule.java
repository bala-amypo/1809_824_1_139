package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

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

    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TransferRule() {
    }

    public TransferRule(University sourceUniversity,
                        University targetUniversity,
                        Double minimumOverlapPercentage,
                        Integer creditHourTolerance,
                        Boolean active) {
        this.sourceUniversity = sourceUniversity;
        this.targetUniversity = targetUniversity;
        this.minimumOverlapPercentage = minimumOverlapPercentage;
        this.creditHourTolerance = creditHourTolerance;
        this.active = active;
    }

    
    @PrePersist
    public void onCreate() {
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ---------- getters & setters ----------
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

    public Double getMinimumOverlapPercentage() {
        return minimumOverlapPercentage;
    }

    public void setMinimumOverlapPercentage(Double minimumOverlapPercentage) {
        this.minimumOverlapPercentage = minimumOverlapPercentage;
    }

    public Integer getCreditHourTolerance() {
        return creditHourTolerance;
    }

    public void setCreditHourTolerance(Integer creditHourTolerance) {
        this.creditHourTolerance = creditHourTolerance;
    }

    public Boolean isActive() {
        return active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
