package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_topics")
public class CourseContentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Course course;

    public CourseContentTopic() {}
    public CourseContentTopic(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
