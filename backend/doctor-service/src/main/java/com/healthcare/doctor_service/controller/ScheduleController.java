package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.entity.Schedule;
import com.healthcare.doctor_service.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping
    public Schedule create(@RequestBody Schedule schedule) {
        return service.saveSchedule(schedule);
    }

    @GetMapping("/{doctorId}")
    public List<Schedule> getByDoctor(@PathVariable Long doctorId) {
        return service.getByDoctor(doctorId);
    }
}