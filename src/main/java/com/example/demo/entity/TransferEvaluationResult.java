package com.example.demo.entity;

public class TransferEvaluationResult {

    private Course sourceCourse;
    private Course targetCourse;
    private double matchPercentage;
    private boolean eligible;

    public void setSourceCourse(Course sourceCourse) {
        this.sourceCourse = sourceCourse;
    }

    public void setTargetCourse(Course targetCourse) {
        this.targetCourse = targetCourse;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public Course getSourceCourse() {
        return sourceCourse;
    }

    public Course getTargetCourse() {
        return targetCourse;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public boolean isEligible() {
        return eligible;
    }
}
