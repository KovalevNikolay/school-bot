package ru.kovalev.schoolbot.handler.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kovalev.schoolbot.parser.TelegramCommandParser;
import ru.kovalev.schoolbot.service.TelegramMessageSender;
import ru.kovalev.schoolbot.service.UserService;
import ru.kovalev.schoolbot.util.BotUtil;

@Component
@RequiredArgsConstructor
public class IdCommandHandler implements CommandHandler {

    private final TelegramCommandParser commandParser;
    private final UserService userService;
    private final TelegramMessageSender messageSender;

    @Override
    public void handle(Update update) {
        String message = update.getCallbackQuery().getData();
        Integer selectedId = commandParser.parseId(message);
        Long userId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        userService.findById(userId).ifPresent(userDto -> {
            userDto.setRoleId(selectedId);
            userService.update(userDto);
            messageSender.sendEditMessageText(userId, messageId, BotUtil.MENU_DESCRIPTION, BotUtil.MAIN_MENU);
        });
    }

    @Override
    public String getCommand() {
        return BotUtil.ROLE_ID_COMMAND;
    }
}
