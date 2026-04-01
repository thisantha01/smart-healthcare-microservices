package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.entity.Prescription;

import java.util.List;

public interface PrescriptionService {
    Prescription save(Prescription prescription);
    List<Prescription> getByPatient(Long patientId);
}