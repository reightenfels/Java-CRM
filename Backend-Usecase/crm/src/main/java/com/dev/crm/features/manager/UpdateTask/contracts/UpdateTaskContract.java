package com.dev.crm.features.manager.UpdateTask.contracts;

import java.time.LocalDateTime;
import java.util.Set;

public interface UpdateTaskContract {
    /**
     * Обновляет данные задачи
     * @param id - ID
     * @param title - Заголовок
     * @param price - Цена
     * @param deadline - Дедлайн
     * @param statusId - ID статуса
     * @param responsibleId - ID ответственного
     * @param clientId - ID клиента
     * @param version - Версия
     * @return - ID обновленной задачи
     */
    public Long updateTask(
        Long id,
        String title,
        Long price,
        LocalDateTime deadline,
        Long statusId,
        Long responsibleId,
        Long clientId,
        int version
    );

    /**
     * Обновляет исполнителей задачи
     * @param taskId - ID задачи
     * @param performersIds - Список ID исполнителей
     * @param version - Версия задачи
     * @return - ID обновленной задачи
     */
    public Long updateTaskPerformers(
        Long taskId, 
        Set<Long> performersIds,
        int version
    );
}
