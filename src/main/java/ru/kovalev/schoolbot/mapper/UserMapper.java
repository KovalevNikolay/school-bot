package ru.kovalev.schoolbot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kovalev.schoolbot.model.dto.UserDto;
import ru.kovalev.schoolbot.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto dto);

    UserDto toDto(User entity);
}
