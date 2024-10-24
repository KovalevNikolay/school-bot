package ru.kovalev.schoolbot.handler.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kovalev.schoolbot.factory.RoleHandlerFactory;
import ru.kovalev.schoolbot.formatter.ScheduleFormatter;
import ru.kovalev.schoolbot.handler.role.RoleHandler;
import ru.kovalev.schoolbot.model.DayOfWeek;
import ru.kovalev.schoolbot.model.dto.ScheduleDto;
import ru.kovalev.schoolbot.repository.UserRepository;
import ru.kovalev.schoolbot.service.TelegramMessageSender;
import ru.kovalev.schoolbot.util.BotUtil;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public abstract class DayCommandHandler implements CommandHandler {

    private final UserRepository userRepository;
    private final TelegramMessageSender messageSender;
    private final RoleHandlerFactory roleHandlerFactory;
    private final ScheduleFormatter scheduleFormatter;

    protected abstract LocalDate getTargetDate();

    @Override
    public void handle(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        LocalDate currentDate = getTargetDate();
        DayOfWeek day = DayOfWeek.valueOf(currentDate.getDayOfWeek().toString());
        userRepository.findById(chatId).ifPresent(userDto -> {
            RoleHandler handler = roleHandlerFactory.getHandler(userDto.getRole());
            List<ScheduleDto> schedule = handler.getScheduleOnDay(userDto.getRoleId(), day);
            String scheduleOnDay = scheduleFormatter.getScheduleOnDay(schedule, currentDate);
            messageSender.sendEditMessageText(chatId, messageId, scheduleOnDay, BotUtil.MAIN_MENU);
        });
    }
}