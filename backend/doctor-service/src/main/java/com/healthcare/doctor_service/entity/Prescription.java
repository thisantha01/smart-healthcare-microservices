package com.healthcare.doctor_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    private Long doctorId;
    private Long patientId;

    @Column(length = 1000)
    private String medicines;

    @Column(length = 1000)
    private String notes;

    private LocalDateTime createdAt;
}