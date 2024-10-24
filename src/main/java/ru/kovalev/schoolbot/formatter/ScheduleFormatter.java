package ru.kovalev.schoolbot.formatter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.kovalev.schoolbot.model.dto.ScheduleDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ScheduleFormatter {

    public String getScheduleOnDay(List<ScheduleDto> schedule, LocalDate date) {
        StringBuilder message = new StringBuilder();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = date.format(dateFormatter);

        message.append("\n*").append(StringUtils.capitalize(formattedDate)).append("*\n\n");

        Map<Integer, List<ScheduleDto>> lessonsByNumber = schedule.stream()
                .collect(Collectors.groupingBy(entry -> entry.getLesson().getLessonNumber()));



        for (Map.Entry<Integer, List<ScheduleDto>> entry : lessonsByNumber.entrySet()) {
            List<ScheduleDto> lessons = entry.getValue();
            ScheduleDto firstLesson = lessons.getFirst();

            String startTime = firstLesson.getLesson().getStartTime().format(timeFormatter);
            String endTime = firstLesson.getLesson().getEndTime().format(timeFormatter);

            message.append(firstLesson.getLesson().getLessonNumber()).append("\\. ")
                    .append(startTime).append("–").append(endTime).append("\n");

            for (ScheduleDto lesson : lessons) {
                message.append("     *").append(lesson.getDiscipline().getName()).append("*\n")
                        .append("     ").append(lesson.getTeacher().getFullName()).append("\n")
                        .append("     *").append(lesson.getClassRoom().getName()).append("*\n");
            }
            message.append("\n");
        }

        return message.toString();
    }
}
