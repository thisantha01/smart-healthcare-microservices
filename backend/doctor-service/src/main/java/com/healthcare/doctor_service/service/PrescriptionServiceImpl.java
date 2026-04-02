package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.dto.PrescriptionDTO;
import com.healthcare.doctor_service.dto.PrescriptionRequestDTO;
import com.healthcare.doctor_service.entity.Prescription;
import com.healthcare.doctor_service.repository.PrescriptionRepository;
import com.healthcare.doctor_service.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repository;

    @Override
    public PrescriptionDTO createPrescription(PrescriptionRequestDTO prescriptionDTO) {
        Prescription prescription = new Prescription();
        prescription.setDoctorId(prescriptionDTO.getDoctorId());
        prescription.setPatientId(prescriptionDTO.getPatientId());
        prescription.setMedicines(prescriptionDTO.getMedicines());
        prescription.setNotes(prescriptionDTO.getNotes());
        prescription.setCreatedAt(LocalDateTime.now());
        
        Prescription savedPrescription = repository.save(prescription);
        return convertToDTO(savedPrescription);
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionsByPatientId(Long patientId) {
        return repository.findByPatientId(patientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionsByDoctorId(Long doctorId) {
        return repository.findByDoctorId(doctorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PrescriptionDTO convertToDTO(Prescription prescription) {
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setPrescriptionId(prescription.getPrescriptionId());
        dto.setDoctorId(prescription.getDoctorId());
        dto.setPatientId(prescription.getPatientId());
        dto.setMedicines(prescription.getMedicines());
        dto.setNotes(prescription.getNotes());
        dto.setCreatedAt(prescription.getCreatedAt());
        return dto;
    }
}