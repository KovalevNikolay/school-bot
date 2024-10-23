package ru.kovalev.schoolbot.factory;

import org.springframework.stereotype.Component;
import ru.kovalev.schoolbot.exception.RoleHandlerException;
import ru.kovalev.schoolbot.handler.role.RoleHandler;
import ru.kovalev.schoolbot.model.Role;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoleHandlerFactory {

    private final Map<Role, RoleHandler> handlers;

    public RoleHandlerFactory(List<RoleHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(Collectors.toMap(RoleHandler::getRole, handler -> handler));
    }

    public RoleHandler getHandler(Role role) {
        return Optional.ofNullable(handlers.get(role))
                .orElseThrow(() -> new RoleHandlerException("Обработчик для указанной роли не был найден. " + role));
    }
}
