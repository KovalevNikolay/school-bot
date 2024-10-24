package ru.kovalev.schoolbot.handler.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kovalev.schoolbot.model.DayOfWeek;
import ru.kovalev.schoolbot.model.Role;
import ru.kovalev.schoolbot.model.dto.ScheduleDto;
import ru.kovalev.schoolbot.service.ScheduleService;
import ru.kovalev.schoolbot.service.TeacherService;
import ru.kovalev.schoolbot.util.BotUtil;

import java.util.List;


@Component
@RequiredArgsConstructor
public class TeacherRoleHandler implements RoleHandler {

    private final TeacherService teacherService;
    private final ScheduleService scheduleService;

    @Override
    public List<List<InlineKeyboardButton>> createRows() {
        return teacherService.findAll().stream()
                .map(item -> InlineKeyboardButton.builder()
                        .callbackData(BotUtil.ROLE_ID_COMMAND + " " + item.getId())
                        .text(item.getFullName())
                        .build()
                )
                .map(List::of)
                .toList();
    }

    @Override
    public List<ScheduleDto> getScheduleOnDay(Integer id, DayOfWeek dayOfWeek) {
        return scheduleService.findAllByTeacherIdAndDayOfWeek(id, dayOfWeek);
    }

    @Override
    public List<ScheduleDto> getScheduleOnWeek(Integer id) {
        return scheduleService.findAllByTeacherId(id);
    }

    @Override
    public Role getRole() {
        return Role.TEACHER;
    }
}
