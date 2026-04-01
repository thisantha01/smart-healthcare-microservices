package com.healthcare.doctor_service.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DoctorRequestDTO {
    private String name;
    private String email;
    private String specialty;
    private String hospital;
    private Double consultationFee;
}