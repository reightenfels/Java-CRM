package com.dev.crm.contracts.services;

import java.util.List;
import java.util.Optional;

import com.dev.crm.entities.Task;

public interface TaskServiceContract {
    /**
     * Возвращает задачу по ID
     * @param Long taskId
     * @return - Модель задачи
     */
    public Optional<Task> findById(Long taskId);

    /**
     * Обновляет статус задачи
     * @param Long statusId
     * @param Long taskId
     * @return - Статус выполнения
     */
    public Boolean updateStatus(Long statusId, Long taskId);

    /**
     * Возвращает задачи с определенным статусом
     * @param Long statusId
     * @return - список задач
     */
    public List<Task> findByStatusId(Long statusId);

    /**
     * Обновляет задачу
     * @param Task task
     * @return - ID обновленной задачи
     */
    public Long update(Task task);

    /**
     * Удаляет задачу
     * @param Long taskId
     * @return - ID удаленной задачи
     */
    public Long delete(Long taskId);

    /**
     * Создает задачу
     * @param Task task
     * @return - ID созданной задачи
     */
    public Long create(Task task);

    /**
     * Обозначает задачу редактируемой в данный момент
     * @param Long taskId
     * @return - Статус выполнения
     */
    public Boolean makeEditingOn(Long taskId);

    /**
     * Снимает флаг редактируемости в данный момент с задачи
     * @param Long taskId
     * @return - Статус выполнения
     */
    public Boolean makeEditingOff(Long taskId);
}
