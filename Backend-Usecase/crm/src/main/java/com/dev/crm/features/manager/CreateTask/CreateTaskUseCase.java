package com.dev.crm.features.manager.CreateTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.CreateTask.contracts.CreateTaskContract;
import com.dev.crm.features.manager.CreateTask.dto.CreateTaskRequest;
import com.dev.crm.features.manager.CreateTask.dto.CreateTaskResponse;
import com.dev.crm.features.manager.CreateTask.services.CreateTaskNotificationService;

@Service
public class CreateTaskUseCase {
    private final CreateTaskContract createTaskService;
    private final CreateTaskNotificationService notificationService;

    public CreateTaskUseCase(
        CreateTaskContract createTaskService,
        CreateTaskNotificationService notificationService
    ) {
        this.createTaskService = createTaskService;
        this.notificationService = notificationService;
    }

    @Transactional
    public CreateTaskResponse handle(CreateTaskRequest request) {
        Long newTaskId = createTaskService.createTask(
            request.getTitle(),
            request.getPrice(),
            request.getDeadline(),
            request.getStatusId(),
            request.getResponsibleId(),
            request.getClientId()
        );

        Long newDialogId = createTaskService.createTaskDialog(newTaskId, null);

        notificationService.send(newTaskId, newDialogId);

        return new CreateTaskResponse(
            newTaskId,
            newDialogId
        );
    }
}
