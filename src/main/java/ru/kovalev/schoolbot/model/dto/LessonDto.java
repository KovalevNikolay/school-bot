package ru.kovalev.schoolbot.model.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class LessonDto {
    private Integer id;
    private Integer lessonNumber;
    private LocalTime startTime;
    private LocalTime endTime;
}
