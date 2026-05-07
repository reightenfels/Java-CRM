package com.dev.crm.features.manager.CreateTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.CreateTask.contracts.CreateTaskContract;
import com.dev.crm.features.manager.CreateTask.dto.CreateTaskRequest;
import com.dev.crm.features.manager.CreateTask.dto.CreateTaskResponse;

@Service
public class CreateTaskUseCase {
    private final CreateTaskContract createTaskService;

    public CreateTaskUseCase(CreateTaskContract createTaskService) {
        this.createTaskService = createTaskService;
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

        // TODO: Добавить событие создания задачи
        // TODO: Добавить событие создания диалога

        return new CreateTaskResponse(
            newTaskId,
            newDialogId
        );
    }
}
