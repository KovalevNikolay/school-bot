package ru.kovalev.schoolbot.handler.role;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kovalev.schoolbot.model.Role;

import java.util.List;

public interface RoleHandler {
    List<List<InlineKeyboardButton>> createRows();

    Role getRole();
}
