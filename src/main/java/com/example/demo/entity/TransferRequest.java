package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "transfer_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_program_id")
    private Program sourceProgram;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_program_id")
    private Program targetProgram;

    @ElementCollection
    @CollectionTable(name = "transfer_request_courses", joinColumns = @JoinColumn(name = "transfer_request_id"))
    private List<CompletedCourse> completedCourses = new ArrayList<>();

    @Column(nullable = false)
    private String status; // PENDING, APPROVED, REJECTED

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Embeddable
    public static class CompletedCourse {
        private String code;
        private String grade;
        private Double credits;
    }
}
