package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.dto.PrescriptionDTO;
import com.healthcare.doctor_service.dto.PrescriptionRequestDTO;
import com.healthcare.doctor_service.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService service;

    @PostMapping
    public ResponseEntity<PrescriptionDTO> create(@Valid @RequestBody PrescriptionRequestDTO prescriptionDTO) {
        PrescriptionDTO createdPrescription = service.createPrescription(prescriptionDTO);
        return new ResponseEntity<>(createdPrescription, HttpStatus.CREATED);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PrescriptionDTO>> getByPatientId(@PathVariable Long patientId) {
        List<PrescriptionDTO> prescriptions = service.getPrescriptionsByPatientId(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PrescriptionDTO>> getByDoctorId(@PathVariable Long doctorId) {
        List<PrescriptionDTO> prescriptions = service.getPrescriptionsByDoctorId(doctorId);
        return ResponseEntity.ok(prescriptions);
    }
}