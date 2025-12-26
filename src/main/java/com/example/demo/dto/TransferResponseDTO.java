package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {
    private Boolean isEligible;
    private Double overlapPercentage;
    private String notes;
}
