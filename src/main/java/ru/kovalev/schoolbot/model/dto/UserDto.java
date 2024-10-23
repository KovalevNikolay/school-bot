package ru.kovalev.schoolbot.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.kovalev.schoolbot.model.Role;

@Data
@Builder
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String bio;
    private Role role;
    private Integer roleId;

}