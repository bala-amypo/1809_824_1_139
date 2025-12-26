package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transfer_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private University sourceUniversity;

    @ManyToOne
    private University targetUniversity;

    private Double minimumOverlapPercentage;

    private int creditHourTolerance;

    private boolean active = true;
}
