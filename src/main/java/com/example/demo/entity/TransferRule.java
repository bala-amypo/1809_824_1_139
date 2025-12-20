package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_rules")
public class TransferRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceUniversity;

    private String targetUniversity;

    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;

    private Boolean active;

    public TransferRule() {
    }

    public TransferRule(Long id,String sourceUniversity, String targetUniversity,
                        Double minimumOverlapPercentage,Integer creditHourTolerance, Boolean active) {
        this.id=id;
        this.sourceUniversity = sourceUniversity;

        this.targetUniversity = targetUniversity;
        this.minimumOverlapPercentage = minimumOverlapPercentage;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceUniversity() {
        return sourceUniversity;
    }

    public void setSourceUniversity(String sourceUniversity) {
        this.sourceUniversity = sourceUniversity;
    }

    public String getTargetUniversity() {
        return targetUniversity;
    }

    public void setTargetUniversity(String targetUniversity) {
        this.targetUniversity = targetUniversity;
    }

    public Double getMinimumOverlapPercentage() {
        return minimumOverlapPercentage;
    }

    public void setMinimumOverlapPercentage(Double minimumPercentage) {
        this.minimumPercentage = minimumPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
}
