package com.dev.crm.features.manager.CreateTask.contracts;

import java.time.LocalDateTime;

public interface CreateTaskContract {
    /**
     * Создает задачу
     * @param title - Название
     * @param price - Цена в копейках
     * @param deadline - Дата и время дедлайна
     * @param statusId - ID статуса
     * @param responsibleId - ID ответственного
     * @param clientId - ID клиента
     * @return
     */
    public Long createTask(
        String title,
        Long price,
        LocalDateTime deadline,
        Long statusId,
        Long responsibleId,
        Long clientId
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
