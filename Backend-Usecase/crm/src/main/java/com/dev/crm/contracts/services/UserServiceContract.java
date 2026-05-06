package com.dev.crm.contracts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dev.crm.dto.command.user.UpdateUserCommand;
import com.dev.crm.entities.User;

public interface UserServiceContract {
    /**
     * Возвращает список всех пользователей
     * @return - список пользователей
     */
    public List<User> findAll();

    /**
     * Возвращает пользователя по ID
     * @param userId - ID пользователя
     * @return - пользователь
     */
    public Optional<User> findById(Long userId);

    /**
     * Возвращает пользователей по поиску
     * @param search - строка поиска
     * @return - список пользователей
     */
    public List<User> search(String search);

    /**
     * Обновляет данные пользователя в БД
     * @param updateUserCommand - record с данными пользователя
     * @return - ID обновления
     */
    public Long update(UpdateUserCommand updateUserCommand);

    /**
     * Удаляет пользователя
     * @param userId - ID модели пользователя
     * @return - ID удаленного пользователя
     */
    public Long delete(Long userId);

    /**
     * Проверяет существует ли пользователь с данным логином
     * @param login - логин пользователя
     * @return - Да\нет
     */
    public Boolean existsByLogin(String login);

    /**
     * Возвращает пользователя по логину
     * @param username - логин пользователя
     * @return - Сущность авторизуемого пользователя
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
