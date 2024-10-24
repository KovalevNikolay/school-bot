package ru.kovalev.schoolbot.handler.command;

import org.springframework.stereotype.Component;
import ru.kovalev.schoolbot.factory.RoleHandlerFactory;
import ru.kovalev.schoolbot.formatter.ScheduleFormatter;
import ru.kovalev.schoolbot.repository.UserRepository;
import ru.kovalev.schoolbot.service.TelegramMessageSender;
import ru.kovalev.schoolbot.util.BotUtil;

import java.time.LocalDate;

@Component
public class TomorrowCommandHandler extends DayCommandHandler {

    private static final Long ONE_DAY = 1L;

    public TomorrowCommandHandler(UserRepository userRepository,
                                  TelegramMessageSender messageSender,
                                  RoleHandlerFactory roleHandlerFactory,
                                  ScheduleFormatter scheduleFormatter) {
        super(userRepository, messageSender, roleHandlerFactory, scheduleFormatter);
    }

    @Override
    public String getCommand() {
        return BotUtil.TOMORROW;
    }

    @Override
    protected LocalDate getTargetDate() {
        return LocalDate.now().plusDays(ONE_DAY);
    }
}
