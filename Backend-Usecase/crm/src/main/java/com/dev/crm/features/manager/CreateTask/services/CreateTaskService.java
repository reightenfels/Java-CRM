package com.dev.crm.features.manager.CreateTask.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Attachment;
import com.dev.crm.entities.Task;
import com.dev.crm.entities.TaskDialog;
import com.dev.crm.entities.TaskStatus;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.CreateTask.contracts.CreateTaskContract;
import com.dev.crm.repositories.AttachmentRepository;
import com.dev.crm.repositories.TaskDialogRepository;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.repositories.TaskStatusRepository;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;

@Service
public class CreateTaskService implements CreateTaskContract {
    private final TaskRepository taskRepository;
    private final TaskDialogRepository dialogRepository;
    private final TaskStatusRepository statusRepository;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;

    public CreateTaskService(
        TaskRepository taskRepository,
        TaskDialogRepository dialogRepository,
        TaskStatusRepository statusRepository,
        UserRepository userRepository,
        AttachmentRepository attachmentRepository
    ) {
        this.taskRepository = taskRepository;
        this.dialogRepository = dialogRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Long createTask(
        String title,
        Long price,
        LocalDateTime deadline,
        Long statusId,
        Long responsibleId,
        Long clientId
    ) {
        Task task = new Task();
        task.setTitle(title);
        task.setPrice(price);
        task.setDeadline(deadline);
        
        TaskStatus status = statusRepository.findById(statusId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден статус с ID: " + statusId)
        );

        task.setStatus(status);

        User responsible = userRepository.findById(responsibleId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден пользоваель с ID: " + responsibleId)
        );

        task.setResponsible(responsible);

        if (clientId != null) {
            User client = userRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Не найден пользоваель с ID: " + responsibleId)
            );

            task.setClient(client);
        }

        try {
            taskRepository.save(task);
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка при создании задачи: " + e.getMessage());
        }

        return task.getId();
    }

    @Override
    public Long createTaskDialog(
        Long taskId,
        Long avatarId
    ) {
        TaskDialog dialog = new TaskDialog();

        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + taskId)
        );

        dialog.setTask(task);

        if (avatarId != null) {
            Attachment avatar = attachmentRepository.findById(avatarId).orElseThrow(
                () -> new ResourceNotFoundException("Не найдено вложение с ID: " + avatarId)
            );

            dialog.setAvatar(avatar);
        }
        
        try {
            dialogRepository.save(dialog);
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка при создании диалога задачи: " + e.getMessage());
        }

        return dialog.getId();
    }
}
