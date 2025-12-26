package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_evaluations")
public class TransferEvaluationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course sourceCourse;

    @ManyToOne
    private Course targetCourse;

    private boolean transferable;

    private String remarks;

    public TransferEvaluationResult() {}
    public TransferEvaluationResult(Course source, Course target, boolean transferable, String remarks){
        this.sourceCourse = source;
        this.targetCourse = target;
        this.transferable = transferable;
        this.remarks = remarks;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Course getSourceCourse() { return sourceCourse; }
    public void setSourceCourse(Course sourceCourse) { this.sourceCourse = sourceCourse; }
    public Course getTargetCourse() { return targetCourse; }
    public void setTargetCourse(Course targetCourse) { this.targetCourse = targetCourse; }
    public boolean isTransferable() { return transferable; }
    public void setTransferable(boolean transferable) { this.transferable = transferable; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
