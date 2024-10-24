package ru.kovalev.schoolbot.mapper;

import org.mapstruct.Mapper;
import ru.kovalev.schoolbot.model.dto.ScheduleDto;
import ru.kovalev.schoolbot.model.entity.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleDto toDto(Schedule entity);
}
