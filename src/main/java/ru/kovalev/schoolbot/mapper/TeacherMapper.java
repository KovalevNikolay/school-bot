package ru.kovalev.schoolbot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kovalev.schoolbot.model.dto.TeacherDto;
import ru.kovalev.schoolbot.model.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDto toDto(Teacher entity);
}
