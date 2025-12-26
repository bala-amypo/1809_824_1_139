package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    private Long sourceUniversityId;
    private Long targetUniversityId;
    private Long courseId;
}
