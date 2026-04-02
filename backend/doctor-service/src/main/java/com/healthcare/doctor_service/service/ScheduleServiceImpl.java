package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.dto.ScheduleDTO;
import com.healthcare.doctor_service.entity.Schedule;
import com.healthcare.doctor_service.exception.ResourceNotFoundException;
import com.healthcare.doctor_service.repository.ScheduleRepository;
import com.healthcare.doctor_service.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = convertToEntity(scheduleDTO);
        Schedule savedSchedule = repository.save(schedule);
        return convertToDTO(savedSchedule);
    }

    @Override
    public List<ScheduleDTO> getScheduleByDoctorId(Long doctorId) {
        return repository.findByDoctorId(doctorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        Schedule existing = getScheduleEntityById(id);
        existing.setDoctorId(scheduleDTO.getDoctorId());
        existing.setDay(scheduleDTO.getDay());
        existing.setStartTime(scheduleDTO.getStartTime());
        existing.setEndTime(scheduleDTO.getEndTime());
        
        Schedule updatedSchedule = repository.save(existing);
        return convertToDTO(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Schedule not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private Schedule getScheduleEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + id));
    }

    private Schedule convertToEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(dto.getScheduleId());
        schedule.setDoctorId(dto.getDoctorId());
        schedule.setDay(dto.getDay());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        return schedule;
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setScheduleId(schedule.getScheduleId());
        dto.setDoctorId(schedule.getDoctorId());
        dto.setDay(schedule.getDay());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        return dto;
    }
}