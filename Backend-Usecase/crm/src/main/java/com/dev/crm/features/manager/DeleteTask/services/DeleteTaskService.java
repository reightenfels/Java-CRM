package com.dev.crm.features.manager.DeleteTask.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.TaskDialog;
import com.dev.crm.entities.TaskEditors;
import com.dev.crm.features.manager.DeleteTask.contracts.DeleteTaskContract;
import com.dev.crm.repositories.TaskDialogMessagesRepository;
import com.dev.crm.repositories.TaskDialogRepository;
import com.dev.crm.repositories.TaskEditorsRepository;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class DeleteTaskService implements DeleteTaskContract {
    private final TaskEditorsRepository editorsRepository;
    private final TaskRepository taskRepository;
    private final TaskDialogRepository dialogRepository;
    private final TaskDialogMessagesRepository messagesRepository;

    public DeleteTaskService(
        TaskEditorsRepository editorsRepository,
        TaskRepository taskRepository,
        TaskDialogRepository dialogRepository,
        TaskDialogMessagesRepository messagesRepository
    ) {
        this.editorsRepository = editorsRepository;
        this.taskRepository = taskRepository;
        this.dialogRepository = dialogRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public Boolean existsTaskEditors(Long taskId) {
        List<TaskEditors> editors = editorsRepository.findByTaskId(taskId);

        return editors.isEmpty();
    }

    @Override
    public Long deleteTask(Long taskId, int version) {
        return taskRepository.deleteByIdWithVersion(taskId, version);
    }

    @Override
    public Long deleteTaskDialog(Long taskId) {
        TaskDialog dialog = dialogRepository.findByTaskId(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден диалог по задаче для задачи с ID: " + taskId)
        );

        Long deletedDialogId = dialog.getId();

        dialogRepository.delete(dialog);

        return deletedDialogId;
    }

    @Override
    public Long deleteTaskDialogMessages(Long taskId) {
        return messagesRepository.deleteByTaskId(taskId);
    }
}
