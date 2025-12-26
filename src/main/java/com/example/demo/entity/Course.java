package com.example.demo.entity;

public class Course {

    private Long id;
    private String name;
    private int credits;
    private University university;

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
