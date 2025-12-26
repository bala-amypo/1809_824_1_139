package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_mappings",
       uniqueConstraints = @UniqueConstraint(columnNames = {"source_course_id", "target_course_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_course_id")
    private Course sourceCourse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_course_id")
    private Course targetCourse;

    @Column(nullable = false)
    private String equivalencyType; // FULL, PARTIAL, ELECTIVE

    @Column
    private String minGradeRequired;
}
