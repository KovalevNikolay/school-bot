package ru.kovalev.schoolbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kovalev.schoolbot.exception.SendMessageException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramMessageSender {

    private final TelegramBotService telegramBotService;

    public void sendMessage(Long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        send(message);
    }

    private void send(SendMessage message) {
        try {
            telegramBotService.execute(message);
            log.info("Бот ответил пользователю {}", message.getChatId());
        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке сообщения. {}", e.getMessage());
            throw new SendMessageException("Произошла ошибка при отправке сообщения.", e);
        }
    }
}
