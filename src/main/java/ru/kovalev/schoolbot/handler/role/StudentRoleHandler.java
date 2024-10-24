package ru.kovalev.schoolbot.handler.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kovalev.schoolbot.model.DayOfWeek;
import ru.kovalev.schoolbot.model.Role;
import ru.kovalev.schoolbot.model.dto.ScheduleDto;
import ru.kovalev.schoolbot.service.GroupService;
import ru.kovalev.schoolbot.service.ScheduleService;
import ru.kovalev.schoolbot.util.BotUtil;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentRoleHandler implements RoleHandler {

    private final GroupService groupService;
    private final ScheduleService scheduleService;

    @Override
    public List<List<InlineKeyboardButton>> createRows() {
        return groupService.findAll().stream()
                .map(item -> InlineKeyboardButton.builder()
                        .callbackData(BotUtil.ROLE_ID_COMMAND + " " + item.getId())
                        .text(item.getName())
                        .build()
                )
                .map(List::of)
                .toList();
    }

    @Override
    public List<ScheduleDto> getScheduleOnDay(Integer id, DayOfWeek dayOfWeek) {
        return scheduleService.findAllByGroupIdAndDayOfWeek(id, dayOfWeek);
    }

    @Override
    public List<ScheduleDto> getScheduleOnWeek(Integer id) {
        return scheduleService.findAllByGroupId(id);
    }

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}

