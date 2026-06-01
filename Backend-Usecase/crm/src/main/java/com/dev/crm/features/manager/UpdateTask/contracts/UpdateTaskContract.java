package com.dev.crm.features.manager.UpdateTask.contracts;

import java.time.LocalDateTime;
import java.util.Set;

import com.dev.crm.entities.Task;

public interface UpdateTaskContract {
    /**
     * Обновляет данные задачи
     * @param id - ID
     * @param title - Заголовок
     * @param description - Описание
     * @param price - Цена
     * @param deadline - Дедлайн
     * @param statusDisplayName - Название статуса
     * @param responsibleLogin - Логин ответственного
     * @param clientLogin - Логни клиента
     * @param version - Версия
     * @return - обновленню задачу
     */
    public Task updateTask(
        Long id,
        String title,
        String description,
        Long price,
        LocalDateTime deadline,
        String statusDisplayName,
        String responsibleLogin,
        String clientLogin,
        int version
    );

    /**
     * Обновляет исполнителей задачи
     * @param taskId - ID задачи
     * @param employeesLogins - Список логинов исполнителей
     * @param version - Версия задачи
     * @return - ID обновленной задачи
     */
    public Long updateTaskPerformers(
        Long taskId, 
        Set<String> employeesLogins,
        int version
    );
}
