package ru.kovalev.schoolbot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kovalev.schoolbot.model.dto.GroupDto;
import ru.kovalev.schoolbot.model.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDto toDto(Group entity);
}
