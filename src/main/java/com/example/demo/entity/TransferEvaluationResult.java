package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transfer_evaluation_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferEvaluationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isEligibleForTransfer;

    private Double overlapPercentage;

    private String notes;
}
