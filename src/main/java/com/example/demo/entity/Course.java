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

    private String description;   // ✅ added
    private String department;    // ✅ added

    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public Course() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Integer getCreditHours() { return creditHours; }
    public void setCreditHours(Integer creditHours) { this.creditHours = creditHours; }

    public String getDescription() { return description; }     // ✅
    public void setDescription(String description) { this.description = description; }

    public String getDepartment() { return department; }       // ✅
    public void setDepartment(String department) { this.department = department; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }
}
