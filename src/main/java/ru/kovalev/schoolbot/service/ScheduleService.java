package ru.kovalev.schoolbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovalev.schoolbot.mapper.ScheduleMapper;
import ru.kovalev.schoolbot.model.DayOfWeek;
import ru.kovalev.schoolbot.model.dto.ScheduleDto;
import ru.kovalev.schoolbot.repository.ScheduleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public List<ScheduleDto> findAllByTeacherIdAndDayOfWeek(Integer id, DayOfWeek dayOfWeek) {
        return scheduleRepository.findAllByTeacherIdAndDayOfWeek(id, dayOfWeek).stream()
                .map(scheduleMapper::toDto)
                .sorted()
                .toList();
    }

    public List<ScheduleDto> findAllByGroupIdAndDayOfWeek(Integer id, DayOfWeek dayOfWeek) {
        return scheduleRepository.findAllByGroupIdAndDayOfWeek(id, dayOfWeek).stream()
                .map(scheduleMapper::toDto)
                .sorted()
                .toList();
    }

    public List<ScheduleDto> findAllByTeacherId(Integer id) {
        return scheduleRepository.findAllByTeacherId(id).stream()
                .map(scheduleMapper::toDto)
                .sorted()
                .toList();
    }

    public List<ScheduleDto> findAllByGroupId(Integer id) {
        return scheduleRepository.findAllByGroupId(id).stream()
                .map(scheduleMapper::toDto)
                .sorted()
                .toList();
    }
}
