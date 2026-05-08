package com.dev.crm.features.manager.DeleteTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.DeleteTask.contracts.DeleteTaskContract;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskRequest;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskResponse;
import com.dev.crm.features.manager.DeleteTask.services.DeleteTaskNotificationService;
import com.dev.crm.resterrorconfig.exceptions.DeleteEditingEntityException;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;

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
    public DeleteTaskResponse handle(DeleteTaskRequest request, Long currentUserId) {
        if (deleteTaskService.existsTaskEditors(request.getTaskId())) {
            throw new DeleteEditingEntityException("В данный момент задача редактируется другим пользователем");
        }

        Long countDeletes = deleteTaskService.deleteTask(request.getTaskId(), request.getVersion());

        if (countDeletes == 0) {
            throw new OldEntityVersionException("Есть более новая версия данной задачи");
        }

        deleteTaskService.deleteTaskDialogMessages(request.getTaskId());
        Long deletedDialogId = deleteTaskService.deleteTaskDialog(request.getTaskId());

        notificationService.send(request.getTaskId(), deletedDialogId);

        return new DeleteTaskResponse(
            request.getTaskId()
        );
    }
}
