package ru.kovalev.schoolbot.factory;

import org.springframework.stereotype.Component;
import ru.kovalev.schoolbot.handler.command.CommandHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandHandlerFactory {

    private final Map<String, CommandHandler> handlers;

    public CommandHandlerFactory(List<CommandHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(Collectors.toMap(CommandHandler::getCommand, handler -> handler));
    }

    public Optional<CommandHandler> getHandler(String command) {
        return Optional.ofNullable(handlers.get(command));
    }
}