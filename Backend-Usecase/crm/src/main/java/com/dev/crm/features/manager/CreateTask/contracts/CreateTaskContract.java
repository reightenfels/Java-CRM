package com.dev.crm.features.manager.CreateTask.contracts;

import java.time.LocalDateTime;
import java.util.Set;

public interface CreateTaskContract {
    /**
     * Создает задачу
     * @param title - Название
     * @param description - Описание
     * @param price - Цена в копейках
     * @param deadline - Дата и время дедлайна
     * @param statusDisplayName - Название статуса
     * @param responsibleLogin - Логин ответственного
     * @param clientLogin - Логин клиента
     * @param employeesLogins - Логины исполнителей
     * @return
     */
    public Long createTask(
        String title,
        String description,
        Long price,
        LocalDateTime deadline,
        String statusDisplayName,
        String responsibleLogin,
        String clientLogin,
        Set<String> employeesLogins
    );

    /**
     * Создает диалог по задаче
     * @param taskId - ID задачи
     * @param avatarId - ID аватара
     * @return
     */
    public Long createTaskDialog(
        Long taskId,
        Long avatarId
    );
}
