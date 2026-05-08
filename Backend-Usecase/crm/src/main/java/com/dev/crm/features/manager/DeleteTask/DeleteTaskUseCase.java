package com.dev.crm.features.manager.DeleteTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.DeleteTask.contracts.DeleteTaskContract;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskRequest;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskResponse;
import com.dev.crm.resterrorconfig.exceptions.DeleteEditingEntityException;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;

@Service
public class DeleteTaskUseCase {
    private final DeleteTaskContract deleteTaskService;

    public DeleteTaskUseCase(DeleteTaskContract deleteTaskService) {
        this.deleteTaskService = deleteTaskService;
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
        deleteTaskService.deleteTaskDialog(request.getTaskId());

        // TODO: Добавить событие удаления задачи
        // TODO: Добавить событие удаления диалога

        return new DeleteTaskResponse(
            request.getTaskId()
        );
    }
}
