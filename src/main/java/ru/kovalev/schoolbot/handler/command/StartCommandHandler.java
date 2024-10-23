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

import java.util.List;

import static ru.kovalev.schoolbot.util.TelegramBotUtil.ROLE_COMMAND;
import static ru.kovalev.schoolbot.util.TelegramBotUtil.START_COMMAND;
import static ru.kovalev.schoolbot.util.TelegramBotUtil.WELCOME_MESSAGE;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {

    private final UserService userService;
    private final TelegramMessageSender messageSender;

    @Override
    public void handle(Update update) {
        Long chatId = update.getMessage().getChatId();

        if (!userService.existById(chatId)) {
            log.info("Новый пользователь: {}", chatId);
            registerUser(update.getMessage().getChat());
        }

        messageSender.sendMessage(chatId, WELCOME_MESSAGE, createInlineKeyboardMarkup());
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
        userService.createUser(userDto);
    }

    private InlineKeyboardMarkup createInlineKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton studentButton = InlineKeyboardButton.builder()
                .callbackData(ROLE_COMMAND + " " + Role.STUDENT)
                .text("Учащийся")
                .build();

        InlineKeyboardButton teacherButton = InlineKeyboardButton.builder()
                .callbackData(ROLE_COMMAND + " " + Role.TEACHER)
                .text("Преподаватель")
                .build();

        inlineKeyboardMarkup.setKeyboard(List.of(List.of(studentButton, teacherButton)));
        return inlineKeyboardMarkup;
    }

    public String getCommand() {
        return START_COMMAND;
    }
}
