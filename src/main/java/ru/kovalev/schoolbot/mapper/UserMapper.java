package ru.kovalev.schoolbot.mapper;

import org.mapstruct.Mapper;
import ru.kovalev.schoolbot.model.dto.UserDto;
import ru.kovalev.schoolbot.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);

    UserDto toDto(User entity);
}
