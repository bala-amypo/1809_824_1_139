package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_content_topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseContentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topicName;

    private Double weightPercentage;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
