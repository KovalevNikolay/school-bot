package ru.kovalev.schoolbot.handler.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kovalev.schoolbot.factory.RoleHandlerFactory;
import ru.kovalev.schoolbot.handler.role.RoleHandler;
import ru.kovalev.schoolbot.model.Role;
import ru.kovalev.schoolbot.parser.TelegramCommandParser;
import ru.kovalev.schoolbot.service.TelegramMessageSender;
import ru.kovalev.schoolbot.service.UserService;

import java.util.List;

import static ru.kovalev.schoolbot.util.TelegramBotUtil.ROLE_COMMAND;
import static ru.kovalev.schoolbot.util.TelegramBotUtil.SELECT_ROLE_ID_MESSAGE;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleCommandHandler implements CommandHandler {

    private final UserService userService;
    private final RoleHandlerFactory roleHandlerFactory;
    private final TelegramMessageSender messageSender;
    private final TelegramCommandParser commandParser;

    @Override
    public void handle(Update update) {
        Long userId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        Role role = commandParser.parseRole(update.getCallbackQuery().getData());
        userService.getById(userId).ifPresent(userDto -> {
            userDto.setRole(role);
            userService.update(userDto);
            InlineKeyboardMarkup keyboardMarkup = createInlineKeyboardMarkup(role);
            messageSender.sendEditMessageText(userId, messageId, SELECT_ROLE_ID_MESSAGE, keyboardMarkup);
        });
    }

    private InlineKeyboardMarkup createInlineKeyboardMarkup(Role role) {
        RoleHandler handler = roleHandlerFactory.getHandler(role);
        List<List<InlineKeyboardButton>> rows = handler.createRows();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    @Override
    public String getCommand() {
        return ROLE_COMMAND;
    }
}
