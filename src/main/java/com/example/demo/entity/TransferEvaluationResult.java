package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "transfer_evaluation_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "transfer_request_id")
    private TransferRequest transferRequest;

    private Double totalTransferableCredits;

    @ElementCollection
    @CollectionTable(name = "accepted_courses", joinColumns = @JoinColumn(name = "evaluation_result_id"))
    private List<String> acceptedCourses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "missing_core_requirements", joinColumns = @JoinColumn(name = "evaluation_result_id"))
    private List<String> missingCoreRequirements = new ArrayList<>();

    private String remarks;
}
