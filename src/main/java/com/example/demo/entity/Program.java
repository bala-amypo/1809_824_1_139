package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "programs", uniqueConstraints = @UniqueConstraint(columnNames = {"university_id", "name"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "university_id")
    private University university;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String level; // UG, PG, etc.
}
