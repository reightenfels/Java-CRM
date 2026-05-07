package com.dev.crm.features.manager.OpenTaskCard.contracts;

import com.dev.crm.entities.Task;

public interface OpenTaskContract {
    /**
     * Получает задачу
     * @param taskId - ID задачи
     * @return - Модель задачи
     */
    public Task getTask(Long taskId);

    /**
     * Добавляет редактора задачи
     * @param taskId - ID задачи
     * @param userId - ID редактора
     * @return - ID созданной записи
     */
    public Long createTaskEditor(Long taskId, Long userId);
}
