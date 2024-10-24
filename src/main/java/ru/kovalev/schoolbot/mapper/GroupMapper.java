package ru.kovalev.schoolbot.mapper;

import org.mapstruct.Mapper;
import ru.kovalev.schoolbot.model.dto.GroupDto;
import ru.kovalev.schoolbot.model.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto toDto(Group entity);
}
