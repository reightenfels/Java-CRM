package com.dev.crm.contracts.services;

import java.util.List;

import com.dev.crm.entities.TaskStatus;

public interface TaskStatusServiceContract {
    /**
     * Возвращает список всех статусов
     * @return - список статусов
     */
    public List<TaskStatus> findAll();

    /**
     * Создает статус
     * @param TaskStatus taskStatus
     * @return - ID созданного статуса
     */
    public Long create(TaskStatus taskStatus);
}
