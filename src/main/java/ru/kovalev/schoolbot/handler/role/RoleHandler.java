package ru.kovalev.schoolbot.handler.role;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kovalev.schoolbot.model.DayOfWeek;
import ru.kovalev.schoolbot.model.Role;
import ru.kovalev.schoolbot.model.dto.ScheduleDto;

import java.util.List;

public interface RoleHandler {
    List<List<InlineKeyboardButton>> createRows();

    List<ScheduleDto> getScheduleOnDay(Integer id, DayOfWeek dayOfWeek);

    List<ScheduleDto> getScheduleOnWeek(Integer id);

    Role getRole();
}