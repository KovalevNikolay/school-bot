package ru.kovalev.schoolbot.formatter;

import org.springframework.stereotype.Component;
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

        Map<Integer, List<ScheduleDto>> lessonsByNumber = schedule.stream()
                .collect(Collectors.groupingBy(entry -> entry.getLesson().getLessonNumber()));

        message.append("\n*").append(date.format(dateFormatter)).append("*\n\n");

        for (Map.Entry<Integer, List<ScheduleDto>> entry : lessonsByNumber.entrySet()) {
            List<ScheduleDto> lessons = entry.getValue();
            ScheduleDto firstLesson = lessons.getFirst();

            String startTime = firstLesson.getLesson().getStartTime().format(timeFormatter);
            String endTime = firstLesson.getLesson().getEndTime().format(timeFormatter);

            message.append(firstLesson.getLesson().getLessonNumber()).append(". ")
                    .append(startTime).append("–").append(endTime).append("\n");

            for (ScheduleDto lesson : lessons) {
                message.append("     *").append(escapeMarkdownV2(lesson.getDiscipline().getName())).append("*\n")
                        .append("     ").append(escapeMarkdownV2(lesson.getTeacher().getFullName())).append("\n")
                        .append("     ").append(escapeMarkdownV2(lesson.getClassRoom().getName())).append("\n");
            }
            message.append("\n");
        }

        return message.toString();
    }

    private String escapeMarkdownV2(String text) {
        return text.replaceAll("([*_\\[\\]()~`>#+\\-=|{}.!])", "\\\\$1");
    }
}
