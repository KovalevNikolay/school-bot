package ru.kovalev.schoolbot.handler.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kovalev.schoolbot.model.Role;
import ru.kovalev.schoolbot.model.dto.UserDto;
import ru.kovalev.schoolbot.service.TelegramMessageSender;
import ru.kovalev.schoolbot.service.UserService;
import ru.kovalev.schoolbot.util.BotUtil;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {

    private final UserService userService;
    private final TelegramMessageSender messageSender;

    @Override
    public void handle(Update update) {
        Long chatId = update.getMessage().getChatId();
        String message = BotUtil.QUESTION;
        if (!userService.existById(chatId)) {
            log.info("Новый пользователь: {}", chatId);
            registerUser(update.getMessage().getChat());
            message = BotUtil.WELCOME + " " + BotUtil.QUESTION;
        }

        messageSender.sendMessage(chatId, message, createKeyboard());
    }

    private void registerUser(Chat chat) {
        UserDto userDto = UserDto.builder()
                .id(chat.getId())
                .firstname(chat.getFirstName())
                .lastname(chat.getLastName())
                .username(chat.getUserName())
                .bio(chat.getBio())
                .role(null)
                .roleId(null)
                .build();

        log.info("Регистрация нового пользователя: {} ", userDto);
        userService.create(userDto);
    }

    private InlineKeyboardMarkup createKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton studentButton = InlineKeyboardButton.builder()
                .callbackData(BotUtil.ROLE_COMMAND + " " + Role.STUDENT)
                .text("Учащийся")
                .build();

        InlineKeyboardButton teacherButton = InlineKeyboardButton.builder()
                .callbackData(BotUtil.ROLE_COMMAND + " " + Role.TEACHER)
                .text("Преподаватель")
                .build();

        inlineKeyboardMarkup.setKeyboard(List.of(List.of(studentButton, teacherButton)));
        return inlineKeyboardMarkup;
    }

    public String getCommand() {
        return BotUtil.START_COMMAND;
    }
}
