package com.healthcare.doctor_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PrescriptionDTO {
    private Long prescriptionId;
    private Long doctorId;
    private Long patientId;
    private String medicines;
    private String notes;
    private LocalDateTime createdAt;
}
