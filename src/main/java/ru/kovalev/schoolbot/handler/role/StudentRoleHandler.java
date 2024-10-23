package ru.kovalev.schoolbot.handler.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kovalev.schoolbot.model.Role;
import ru.kovalev.schoolbot.service.GroupService;

import java.util.List;

import static ru.kovalev.schoolbot.util.TelegramBotUtil.ROLE_ID_COMMAND;

@Component
@RequiredArgsConstructor
public class StudentRoleHandler implements RoleHandler {

    private final GroupService groupService;

    @Override
    public List<List<InlineKeyboardButton>> createRows() {
        return groupService.findAll().stream()
                .map(item -> InlineKeyboardButton.builder()
                        .callbackData(ROLE_ID_COMMAND + " " + item.getId())
                        .text(item.getName())
                        .build()
                )
                .map(List::of)
                .toList();
    }

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
