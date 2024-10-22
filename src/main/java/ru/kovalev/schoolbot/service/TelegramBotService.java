package ru.kovalev.schoolbot.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.kovalev.schoolbot.exception.RegisterBotException;

@Slf4j
@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private final String username;

    public TelegramBotService(@Value("${telegram.bot.token}") String token,
                              @Value("${telegram.bot.name}") String username) {
        super(token);
        this.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

        }
    }

    @PostConstruct
    public void init() {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(this);
        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при регистрации бота. {}", e.getMessage());
            throw new RegisterBotException("Произошла ошибка при регистрации бота. ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }
}
