package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    List<Doctor> getBySpecialty(String specialty);
    Doctor updateDoctor(Long id, Doctor doctor);
    void deleteDoctor(Long id);
}