package ru.kovalev.schoolbot.model.dto;

import lombok.Data;
import ru.kovalev.schoolbot.model.DayOfWeek;

@Data
public class ScheduleDto implements Comparable<ScheduleDto>{
    private Integer id;
    private DayOfWeek dayOfWeek;
    private LessonDto lesson;
    private DisciplineDto discipline;
    private GroupDto group;
    private TeacherDto teacher;
    private ClassRoomDto classRoom;

    @Override
    public int compareTo(ScheduleDto scheduleDto) {

        int result = this.dayOfWeek.compareTo(scheduleDto.dayOfWeek);

        if (result == 0) {
            result = this.lesson.getLessonNumber().compareTo(scheduleDto.lesson.getLessonNumber());
        }

        return result;
    }
}