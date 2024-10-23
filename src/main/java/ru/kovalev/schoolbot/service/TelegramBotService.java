package ru.kovalev.schoolbot.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.kovalev.schoolbot.exception.RegisterBotException;
import ru.kovalev.schoolbot.factory.CommandHandlerFactory;
import ru.kovalev.schoolbot.parser.TelegramCommandParser;

@Slf4j
@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private final String username;
    private final CommandHandlerFactory commandHandlerFactory;
    private final TelegramCommandParser commandParser;

    public TelegramBotService(@Value("${telegram.bot.token}") String token,
                              @Value("${telegram.bot.name}") String username,
                              @Lazy CommandHandlerFactory commandHandlerFactory,
                              TelegramCommandParser commandParser) {
        super(token);
        this.username = username;
        this.commandHandlerFactory = commandHandlerFactory;
        this.commandParser = commandParser;
    }

    @Override
    public void onUpdateReceived(Update update) {
        commandHandlerFactory.getHandler(extractCommand(update))
                .ifPresent(handler -> handler.handle(update));
    }

    private String extractCommand(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            return commandParser.parseCommand(update.getCallbackQuery().getData());
        }
        return " ";
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
