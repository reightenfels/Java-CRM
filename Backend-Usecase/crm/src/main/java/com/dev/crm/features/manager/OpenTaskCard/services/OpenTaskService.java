package com.dev.crm.features.manager.OpenTaskCard.services;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Task;
import com.dev.crm.entities.TaskEditors;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.OpenTaskCard.contracts.OpenTaskContract;
import com.dev.crm.repositories.TaskEditorsRepository;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;

@Service
public class OpenTaskService implements OpenTaskContract {
    private final TaskRepository taskRepository;
    private final TaskEditorsRepository editorsRepository;
    private final UserRepository userRepository;

    public OpenTaskService(
        TaskRepository taskRepository,
        TaskEditorsRepository editorsRepository,
        UserRepository userRepository
    ) {
        this.taskRepository = taskRepository;
        this.editorsRepository = editorsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + taskId)
        );
    }

    @Override
    public Long createTaskEditor(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + taskId)
        );

        User user = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден пользователь с ID: " + taskId)
        );

        TaskEditors editor = new TaskEditors();
        editor.setTask(task);
        editor.setUser(user);

        try {
            editorsRepository.save(editor);
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка при создании редактора задачи: " + e.getMessage());
        }

        return editor.getId();
    }
}
