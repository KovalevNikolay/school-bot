package ru.kovalev.schoolbot.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@UtilityClass
public class BotUtil {

    public final String START_COMMAND = "/start";
    public final String ROLE_COMMAND = "/role";
    public final String ROLE_ID_COMMAND = "/role_id";
    public final String TODAY = "/today";
    public final String TODAY_TEXT = "Сегодня";
    public final String TOMORROW = "/tomorrow";
    public final String TOMORROW_TEXT = "Завтра";
    public final String WEEK = "/week";
    public final String WEEK_TEXT = "Неделя";
    public final String RESET_SETTINGS = "/reset";
    public final String RESET_SETTINGS_TEXT = "Сбросить настройки";
    public final String CONFIRM_RESET_TEXT = "Да";
    public final String CANCEL_RESET = "/cancel";
    public final String CANCEL_RESET_TEXT = "Отмена";
    public final String WELCOME = "Добро пожаловать!";
    public final String QUESTION = "Вы учащийся или преподаватель?";
    public final String SELECT_ID_MESSAGE = "Выберите нужный пункт из предложенного списка: ";
    public final String MENU_DESCRIPTION = """
            *%s* \\- расписание занятий на сегодня
            *%s* \\- расписание занятий на завтра
            *%s* \\- расписание занятий на неделю
            *%s* \\- выполнить настройку заново
            """.formatted(TODAY_TEXT, TOMORROW_TEXT, WEEK_TEXT, RESET_SETTINGS_TEXT);

    public final InlineKeyboardMarkup MAIN_MENU = new InlineKeyboardMarkup();

    static {
        InlineKeyboardButton today = InlineKeyboardButton.builder()
                .text(TODAY_TEXT)
                .callbackData(TODAY)
                .build();

        InlineKeyboardButton tomorrow = InlineKeyboardButton.builder()
                .text(TOMORROW_TEXT)
                .callbackData(TOMORROW)
                .build();

        InlineKeyboardButton week = InlineKeyboardButton.builder()
                .text(WEEK_TEXT)
                .callbackData(WEEK)
                .build();

        InlineKeyboardButton reset = InlineKeyboardButton.builder()
                .text(RESET_SETTINGS_TEXT)
                .callbackData(RESET_SETTINGS)
                .build();

        MAIN_MENU.setKeyboard(List.of(
                List.of(today, tomorrow),
                List.of(week),
                List.of(reset)
        ));
    }
}