package ru.kovalev.schoolbot.parser;

import org.springframework.stereotype.Component;
import ru.kovalev.schoolbot.model.Role;

@Component
public class TelegramCommandParser {

    private static final String SPACE = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int ROLE_INDEX = 1;
    private static final int ID_INDEX = 1;

    public String parseCommand(String message) {
        return parse(message, COMMAND_INDEX);
    }

    public Role parseRole(String message) {
        return Role.valueOf(parse(message, ROLE_INDEX));
    }

    public Integer parseId(String message) {
        return Integer.valueOf(parse(message, ID_INDEX));
    }

    private String parse(String message, int indexOf) {
        return message.split(SPACE)[indexOf];
    }
}
