package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.entity.Prescription;
import com.healthcare.doctor_service.repository.PrescriptionRepository;
import com.healthcare.doctor_service.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repository;

    @Override
    public Prescription save(Prescription prescription) {
        prescription.setCreatedAt(LocalDateTime.now());
        return repository.save(prescription);
    }

    @Override
    public List<Prescription> getByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}