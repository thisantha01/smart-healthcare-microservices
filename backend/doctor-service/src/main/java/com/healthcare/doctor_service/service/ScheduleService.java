package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);
    List<Schedule> getByDoctor(Long doctorId);
}