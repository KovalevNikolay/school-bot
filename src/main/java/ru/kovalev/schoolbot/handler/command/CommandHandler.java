package ru.kovalev.schoolbot.handler.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {
    void handle(Update update);
    String getCommand();
}
