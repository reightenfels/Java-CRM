package com.dev.crm.features.manager.ChangeTaskStatus.services;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Task;
import com.dev.crm.entities.TaskStatus;
import com.dev.crm.features.manager.ChangeTaskStatus.contracts.ChangeTaskStatusContract;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.repositories.TaskStatusRepository;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;

@Service
public class ChangeTaskStatusService implements ChangeTaskStatusContract {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository statusRepository;

    public ChangeTaskStatusService(
        TaskRepository taskRepository,
        TaskStatusRepository statusRepository
    ) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Long updateTaskStatus(
        Long taskId,
        String statusSystemName,
        int version
    ) {
        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + taskId)
        );

        TaskStatus status = statusRepository.findBySystemName(statusSystemName).orElseThrow(
            () -> new ResourceNotFoundException("Не найден статус с названием: " + statusSystemName)
        );

        task.setStatus(status);

        if (task.getVersion() != version) {
            throw new OldEntityVersionException("Есть более новая версия данной задачи");
        }

        try {
            taskRepository.save(task);
            return task.getId();
        } catch (Exception e) {
            throw new SaveEntityException("Ошибка при сохранении задачи с ID: " + task.getId());
        }
    }
}
