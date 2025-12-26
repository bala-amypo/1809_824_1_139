package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(columnNames = {"courseCode", "university_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;

    private String courseName;

    private int creditHours;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
}
