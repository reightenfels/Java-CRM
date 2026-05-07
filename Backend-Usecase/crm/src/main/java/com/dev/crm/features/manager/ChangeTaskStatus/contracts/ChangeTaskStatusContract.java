package com.dev.crm.features.manager.ChangeTaskStatus.contracts;

public interface ChangeTaskStatusContract {
    /**
     * Обновляет статуст задачи
     * @param taskId - ID задачи
     * @param statusId - ID статуса
     * @param version - Версия
     * @return - Количество обновленных строк
     */
    public Long updateTaskStatus(
        Long taskId,
        Long statusId,
        int version
    );
}
