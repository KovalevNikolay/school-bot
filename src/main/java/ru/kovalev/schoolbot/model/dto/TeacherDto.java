package ru.kovalev.schoolbot.model.dto;

import lombok.Data;

@Data
public class TeacherDto {
    private Integer id;
    private String lastname;
    private String firstname;
    private String patronymic;

    public String getFullName() {
        return lastname + " " + firstname + " " + patronymic;
    }
}
