package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.entity.Schedule;
import com.healthcare.doctor_service.repository.ScheduleRepository;
import com.healthcare.doctor_service.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public List<Schedule> getByDoctor(Long doctorId) {
        return repository.findByDoctorId(doctorId);
    }
}