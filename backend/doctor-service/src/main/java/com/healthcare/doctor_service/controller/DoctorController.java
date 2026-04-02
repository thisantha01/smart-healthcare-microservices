package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.dto.DoctorRequestDTO;
import com.healthcare.doctor_service.dto.DoctorResponseDTO;
import com.healthcare.doctor_service.entity.DoctorStatus;
import com.healthcare.doctor_service.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(@Valid @RequestBody DoctorRequestDTO doctorDTO) {
        DoctorResponseDTO createdDoctor = service.createDoctor(doctorDTO);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAll() {
        List<DoctorResponseDTO> doctors = service.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getById(@PathVariable Long id) {
        DoctorResponseDTO doctor = service.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorResponseDTO>> getBySpecialty(@RequestParam String specialty) {
        List<DoctorResponseDTO> doctors = service.getDoctorsBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> update(@PathVariable Long id, 
                                                   @Valid @RequestBody DoctorRequestDTO doctorDTO) {
        DoctorResponseDTO updatedDoctor = service.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DoctorResponseDTO> updateStatus(@PathVariable Long id, 
                                                          @RequestParam DoctorStatus status) {
        DoctorResponseDTO updatedDoctor = service.updateDoctorStatus(id, status);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<DoctorResponseDTO> approveDoctor(@PathVariable Long id) {
        DoctorResponseDTO updatedDoctor = service.updateDoctorStatus(id, DoctorStatus.APPROVED);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<DoctorResponseDTO> rejectDoctor(@PathVariable Long id) {
        DoctorResponseDTO updatedDoctor = service.updateDoctorStatus(id, DoctorStatus.REJECTED);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}