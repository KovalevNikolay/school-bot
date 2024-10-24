package ru.kovalev.schoolbot.mapper;

import org.mapstruct.Mapper;
import ru.kovalev.schoolbot.model.dto.TeacherDto;
import ru.kovalev.schoolbot.model.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto toDto(Teacher entity);
}