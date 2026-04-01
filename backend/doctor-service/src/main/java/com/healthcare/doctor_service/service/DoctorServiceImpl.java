package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.entity.Doctor;
import com.healthcare.doctor_service.repository.DoctorRepository;
import com.healthcare.doctor_service.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Doctor> getBySpecialty(String specialty) {
        return repository.findBySpecialty(specialty);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existing = getDoctorById(id);
        existing.setName(doctor.getName());
        existing.setSpecialty(doctor.getSpecialty());
        existing.setHospital(doctor.getHospital());
        existing.setFee(doctor.getFee());
        return repository.save(existing);
    }

    @Override
    public void deleteDoctor(Long id) {
        repository.deleteById(id);
    }
}