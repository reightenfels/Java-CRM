package com.dev.crm.services.users;

import java.util.List;
import java.util.Optional;

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
     * @param user - модель пользователя
     * @return - ID обновления
     */
    public Long update(User user);

    /**
     * Создает пользователя в БД
     * @param user - модель пользователя
     * @return - ID созданного пользователя
     */
    public Long create(User user);

    /**
     * Удаляет пользователя
     * @param userId - ID модели пользователя
     * @return - ID удаленного пользователя
     */
    public Long delete(Long userId);
}
