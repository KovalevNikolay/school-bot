package ru.kovalev.schoolbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovalev.schoolbot.model.DayOfWeek;
import ru.kovalev.schoolbot.model.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAllByTeacherIdAndDayOfWeek(Integer teacherId, DayOfWeek dayOfWeek);

    List<Schedule> findAllByGroupIdAndDayOfWeek(Integer groupId, DayOfWeek dayOfWeek);

    List<Schedule> findAllByGroupId(Integer groupId);

    List<Schedule> findAllByTeacherId(Integer teacherId);

}
