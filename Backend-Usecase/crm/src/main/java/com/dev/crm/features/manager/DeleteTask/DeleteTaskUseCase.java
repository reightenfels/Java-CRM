package com.dev.crm.features.manager.DeleteTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.entities.Task;
import com.dev.crm.features.manager.DeleteTask.contracts.DeleteTaskContract;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskRequest;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskResponse;
import com.dev.crm.features.manager.DeleteTask.services.DeleteTaskNotificationService;
import com.dev.crm.resterrorconfig.exceptions.DeleteEditingEntityException;

@Service
public class DeleteTaskUseCase {
    private final DeleteTaskContract deleteTaskService;
    private final DeleteTaskNotificationService notificationService;

    public DeleteTaskUseCase(
        DeleteTaskContract deleteTaskService,
        DeleteTaskNotificationService notificationService
    ) {
        this.deleteTaskService = deleteTaskService;
        this.notificationService = notificationService;
    }

    @Transactional
    public DeleteTaskResponse handle(
        Long taskId,
        DeleteTaskRequest request, 
        Long currentUserId) {
        if (deleteTaskService.existsTaskEditors(taskId, currentUserId)) {
            throw new DeleteEditingEntityException("В данный момент задача редактируется другим пользователем");
        }

        Task deletedTask = deleteTaskService.deleteTask(taskId, request.getVersion());

        notificationService.send(taskId, deletedTask.getTaskDialog().getId());

        return new DeleteTaskResponse(
            taskId
        );
    }
}
