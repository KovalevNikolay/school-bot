package ru.kovalev.schoolbot.exception;

public class RegisterBotException extends RuntimeException {

    public RegisterBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
