package com.dev.crm.services.user;

import java.util.List;
import java.util.Optional;

import com.dev.crm.dto.command.user.CreateUserCommand;
import com.dev.crm.dto.command.user.UpdateUserCommand;
import com.dev.crm.entities.User;

public interface IUserService {
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
     * Создает пользователя в БД
     * @param createUserCommand - record с данными пользователя
     * @return - ID созданного пользователя
     */
    public Long create(CreateUserCommand createUserCommand);

    /**
     * Удаляет пользователя
     * @param userId - ID модели пользователя
     * @return - ID удаленного пользователя
     */
    public Long delete(Long userId);
}
