package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.dto.ScheduleDTO;
import com.healthcare.doctor_service.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping
    public ResponseEntity<ScheduleDTO> create(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO createdSchedule = service.createSchedule(scheduleDTO);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ScheduleDTO>> getByDoctorId(@PathVariable Long doctorId) {
        List<ScheduleDTO> schedules = service.getScheduleByDoctorId(doctorId);
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> update(@PathVariable Long id, 
                                           @Valid @RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO updatedSchedule = service.updateSchedule(id, scheduleDTO);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}