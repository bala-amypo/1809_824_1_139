package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private String courseName;
    private Integer creditHours;

    private Boolean active = true;  // <-- added

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Integer getCreditHours() { return creditHours; }
    public void setCreditHours(Integer creditHours) { this.creditHours = creditHours; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }

    // Optional: helper for TransferEvaluationServiceImpl
    public boolean isActive() {  // <-- add this to match your service
        return Boolean.TRUE.equals(active);
    }
}
