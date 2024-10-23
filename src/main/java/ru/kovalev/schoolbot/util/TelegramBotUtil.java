package ru.kovalev.schoolbot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TelegramBotUtil {

    public final String START_COMMAND = "/start";
    public final String ROLE_COMMAND = "/role";
    public final String ROLE_ID_COMMAND = "/role_id";
    public final String WELCOME_MESSAGE = "Добро пожаловать! Вы учащийся или преподаватель?";
    public final String SELECT_ROLE_ID_MESSAGE = "Выберите нужный пункт из предложенного списка: ";
}
