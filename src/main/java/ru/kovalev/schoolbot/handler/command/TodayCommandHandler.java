package ru.kovalev.schoolbot.handler.command;

import org.springframework.stereotype.Component;
import ru.kovalev.schoolbot.factory.RoleHandlerFactory;
import ru.kovalev.schoolbot.formatter.ScheduleFormatter;
import ru.kovalev.schoolbot.repository.UserRepository;
import ru.kovalev.schoolbot.service.TelegramMessageSender;
import ru.kovalev.schoolbot.util.BotUtil;

import java.time.LocalDate;

@Component
public class TodayCommandHandler extends DayCommandHandler {
    public TodayCommandHandler(UserRepository userRepository,
                               TelegramMessageSender messageSender,
                               RoleHandlerFactory roleHandlerFactory,
                               ScheduleFormatter scheduleFormatter) {
        super(userRepository, messageSender, roleHandlerFactory, scheduleFormatter);
    }

    @Override
    protected LocalDate getTargetDate() {
        return LocalDate.now();
    }

    @Override
    public String getCommand() {
        return BotUtil.TODAY;
    }
}
