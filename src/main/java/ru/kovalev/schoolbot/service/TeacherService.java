package ru.kovalev.schoolbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovalev.schoolbot.mapper.TeacherMapper;
import ru.kovalev.schoolbot.model.dto.TeacherDto;
import ru.kovalev.schoolbot.repository.TeacherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public List<TeacherDto> findAll() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDto)
                .toList();
    }
}
