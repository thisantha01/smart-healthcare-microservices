package com.healthcare.doctor_service.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String name;
    private String email;
    private String specialty;
    private String hospital;
    private Double fee;

    @Enumerated(EnumType.STRING)
    private DoctorStatus status;
}