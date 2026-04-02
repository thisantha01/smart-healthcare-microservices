package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.dto.PrescriptionDTO;
import com.healthcare.doctor_service.dto.PrescriptionRequestDTO;

import java.util.List;

public interface PrescriptionService {
    PrescriptionDTO createPrescription(PrescriptionRequestDTO prescriptionDTO);
    List<PrescriptionDTO> getPrescriptionsByPatientId(Long patientId);
    List<PrescriptionDTO> getPrescriptionsByDoctorId(Long doctorId);
}