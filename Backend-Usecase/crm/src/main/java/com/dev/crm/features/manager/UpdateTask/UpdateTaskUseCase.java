package com.dev.crm.features.manager.UpdateTask;

import org.springframework.stereotype.Service;

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
        updateTaskService.updateTask(
            request.getTaskId(),
            request.getTitle(),
            request.getPrice(),
            request.getDeadline(),
            request.getStatusId(),
            request.getResponsibleId(),
            request.getClientId(),
            request.getVersion()
        );

        updateTaskService.updateTaskPerformers(
            request.getTaskId(),
            request.getPerformersIds(),
            request.getVersion() + 1
        );

        notificationService.send(request.getTaskId());

        return new UpdateTaskResponse(
            request.getTaskId()
        );
    }
}
