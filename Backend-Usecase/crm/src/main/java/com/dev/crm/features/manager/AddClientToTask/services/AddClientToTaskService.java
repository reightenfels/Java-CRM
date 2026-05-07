package com.dev.crm.features.manager.AddClientToTask.services;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.AddClientToTask.contracts.AddClientToTaskContract;
import com.dev.crm.repositories.TaskRepository;

@Service
public class AddClientToTaskService implements AddClientToTaskContract {
    private final TaskRepository taskRepository;

    public AddClientToTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long addClientToTask(
        Long taskId, 
        Long clientId,
        int version
    ) {
        return taskRepository.updateClientId(taskId, clientId, version);
    }
}
