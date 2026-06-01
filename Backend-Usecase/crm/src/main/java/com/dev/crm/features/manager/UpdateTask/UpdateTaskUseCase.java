package com.dev.crm.features.manager.UpdateTask;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Task;
import com.dev.crm.features.manager.UpdateTask.contracts.UpdateTaskContract;
import com.dev.crm.features.manager.UpdateTask.dto.UpdateTaskRequest;
import com.dev.crm.features.manager.UpdateTask.dto.UpdateTaskResponse;
import com.dev.crm.features.manager.UpdateTask.services.UpdateTaskNotificationService;

@Service
public class UpdateTaskUseCase {
    private final UpdateTaskContract updateTaskService;
    private final UpdateTaskNotificationService notificationService;

    public UpdateTaskUseCase(
        UpdateTaskContract updateTaskService,
        UpdateTaskNotificationService notificationService
    ) {
        this.updateTaskService = updateTaskService;
        this.notificationService = notificationService;
    }

    public UpdateTaskResponse handle(UpdateTaskRequest request) {
        Task updatedTask = updateTaskService.updateTask(
            request.getTaskId(),
            request.getTitle(),
            request.getDescription(),
            request.getPrice(),
            request.getDeadline(),
            request.getStatusDisplayName(),
            request.getResponsibleLogin(),
            request.getClientLogin(),
            request.getVersion()
        );

        updateTaskService.updateTaskPerformers(
            updatedTask.getId(),
            request.getEmployeesLogins(),
            request.getVersion() + 1
        );

        notificationService.send(updatedTask.getId());

        return new UpdateTaskResponse(
            updatedTask.getId(),
            updatedTask.getVersion()
        );
    }
}
