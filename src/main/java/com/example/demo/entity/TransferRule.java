package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_rules")
public class TransferRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private University sourceUniversity;

    @ManyToOne
    private University targetUniversity;

    @Column(nullable = false)
    private String ruleDescription;

    public TransferRule() {}
    public TransferRule(University source, University target, String ruleDescription){
        this.sourceUniversity = source;
        this.targetUniversity = target;
        this.ruleDescription = ruleDescription;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public University getSourceUniversity() { return sourceUniversity; }
    public void setSourceUniversity(University sourceUniversity) { this.sourceUniversity = sourceUniversity; }
    public University getTargetUniversity() { return targetUniversity; }
    public void setTargetUniversity(University targetUniversity) { this.targetUniversity = targetUniversity; }
    public String getRuleDescription() { return ruleDescription; }
    public void setRuleDescription(String ruleDescription) { this.ruleDescription = ruleDescription; }
}
