package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.dto.DoctorRequestDTO;
import com.healthcare.doctor_service.dto.DoctorResponseDTO;
import com.healthcare.doctor_service.entity.Doctor;
import com.healthcare.doctor_service.entity.DoctorStatus;
import com.healthcare.doctor_service.exception.ResourceNotFoundException;
import com.healthcare.doctor_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        doctor.setHospital(doctorDTO.getHospital());
        doctor.setFee(doctorDTO.getConsultationFee());
        doctor.setStatus(DoctorStatus.PENDING);
        
        Doctor savedDoctor = repository.save(doctor);
        return convertToResponseDTO(savedDoctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        return repository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doctor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return convertToResponseDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getDoctorsBySpecialty(String specialty) {
        return repository.findBySpecialty(specialty).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO doctorDTO) {
        Doctor existing = getDoctorEntityById(id);
        existing.setName(doctorDTO.getName());
        existing.setEmail(doctorDTO.getEmail());
        existing.setSpecialty(doctorDTO.getSpecialty());
        existing.setHospital(doctorDTO.getHospital());
        existing.setFee(doctorDTO.getConsultationFee());
        
        Doctor updatedDoctor = repository.save(existing);
        return convertToResponseDTO(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public DoctorResponseDTO updateDoctorStatus(Long id, DoctorStatus status) {
        Doctor doctor = getDoctorEntityById(id);
        doctor.setStatus(status);
        Doctor updatedDoctor = repository.save(doctor);
        return convertToResponseDTO(updatedDoctor);
    }

    private Doctor getDoctorEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    private DoctorResponseDTO convertToResponseDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setDoctorId(doctor.getDoctorId());
        dto.setName(doctor.getName());
        dto.setEmail(doctor.getEmail());
        dto.setSpecialty(doctor.getSpecialty());
        dto.setHospital(doctor.getHospital());
        dto.setConsultationFee(doctor.getFee());
        dto.setStatus(doctor.getStatus());
        return dto;
    }
}