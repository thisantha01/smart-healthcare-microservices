package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.entity.Prescription;
import com.healthcare.doctor_service.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService service;

    @PostMapping
    public Prescription create(@RequestBody Prescription prescription) {
        return service.save(prescription);
    }

    @GetMapping("/{patientId}")
    public List<Prescription> getByPatient(@PathVariable Long patientId) {
        return service.getByPatient(patientId);
    }
}