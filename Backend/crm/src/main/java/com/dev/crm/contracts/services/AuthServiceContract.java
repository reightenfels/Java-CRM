package com.dev.crm.contracts.services;

import com.dev.crm.dto.command.auth.LoginUserCommand;
import com.dev.crm.dto.command.auth.RegisterUserCommand;
import com.dev.crm.dto.response.auth.AuthResponse;
import com.dev.crm.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthServiceContract {
    /**
     * Регистрирует пользователя в системе
     * @param command - данные нового пользователя
     */
    public void register(RegisterUserCommand command);

    /**
     * Аннулирует все действительные токены пользователя
     * @param user - сущность пользователя
     */
    public void revokeAllToken(User user);

    /**
     * Сохраняет токены пользователя в БД
     * @param accessToken - токен доступа
     * @param refreshToken - токен обновления
     * @param user - сущность пользователя
     */
    public void saveUserToken(String accessToken, String refreshToken, User user);

    /**
     * Аутентифицирует пользователя
     * @param command - данные аутентификации
     * @return - данные с результатом аутентификации
     */
    public AuthResponse authenticate(LoginUserCommand command);

    /**
     * Обновляет токен доступа
     * @param request - объект запроса
     * @param response - объект ответа
     * @return - данные авторизации
     */
    public AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response);
}
