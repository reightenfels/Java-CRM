package com.dev.crm.features.manager.DeleteTask.contracts;

import com.dev.crm.entities.Task;

public interface DeleteTaskContract {
    /**
     * Проверяет есть ли редакторы у задачи
     * @param taskId - ID задачи
     * @param currentUserId - ID текущего пользователя
     * @return Есть\нет
     */
    public Boolean existsTaskEditors(Long taskId, Long currentUserId);

    /**
     * Удаляет задачу
     * @param Long taskId - ID задачи
     * @param int version - Версия задачи
     * @return - Кол-во удаленных строк
     */
    public Task deleteTask(Long taskId, int version);
}
