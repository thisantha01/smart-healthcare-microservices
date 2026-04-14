package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.dto.DoctorRequestDTO;
import com.healthcare.doctor_service.dto.DoctorResponseDTO;
import com.healthcare.doctor_service.entity.DoctorStatus;

import java.util.List;

public interface DoctorService {
    DoctorResponseDTO createDoctor(DoctorRequestDTO doctorDTO);
    List<DoctorResponseDTO> getAllDoctors();
    DoctorResponseDTO getDoctorById(Long id);
    List<DoctorResponseDTO> getDoctorsBySpecialty(String specialty);
    DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO doctorDTO);
    void deleteDoctor(Long id);
    DoctorResponseDTO updateDoctorStatus(Long id, DoctorStatus status);
}