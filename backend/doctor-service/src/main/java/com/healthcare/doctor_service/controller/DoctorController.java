package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.entity.Doctor;
import com.healthcare.doctor_service.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @PostMapping
    public Doctor create(@RequestBody Doctor doctor) {
        return service.saveDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAll() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return service.getDoctorById(id);
    }

    @GetMapping("/search")
    public List<Doctor> getBySpecialty(@RequestParam String specialty) {
        return service.getBySpecialty(specialty);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor doctor) {
        return service.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDoctor(id);
    }
}