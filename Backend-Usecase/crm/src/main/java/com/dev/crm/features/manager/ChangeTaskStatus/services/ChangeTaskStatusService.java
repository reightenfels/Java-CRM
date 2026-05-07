package com.dev.crm.features.manager.ChangeTaskStatus.services;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.ChangeTaskStatus.contracts.ChangeTaskStatusContract;
import com.dev.crm.repositories.TaskRepository;

@Service
public class ChangeTaskStatusService implements ChangeTaskStatusContract {
    private final TaskRepository taskRepository;

    public ChangeTaskStatusService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long updateTaskStatus(
        Long taskId,
        Long statusId,
        int version
    ) {
        return taskRepository.updateStatusId(taskId, statusId, version);
    }
}
