package com.dev.crm.features.manager.DeleteTask.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Task;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.DeleteTask.contracts.DeleteTaskContract;
import com.dev.crm.repositories.TaskEditorsRepository;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class DeleteTaskService implements DeleteTaskContract {
    private final TaskEditorsRepository editorsRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public DeleteTaskService(
        TaskEditorsRepository editorsRepository,
        TaskRepository taskRepository,
        UserRepository userRepository
    ) {
        this.editorsRepository = editorsRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existsTaskEditors(
        Long taskId,
        Long currentUserId
    ) {
        Set<String> editorsLogins = editorsRepository.findTaskEditorsLogins(taskId);
        User currentUser = userRepository.findById(currentUserId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден пользователь с ID: " + currentUserId)
        );

        if (editorsLogins.isEmpty()) {
            return false;
        }

        if (editorsLogins.contains(currentUser.getLogin())) {
            editorsLogins.remove(currentUser.getLogin());

            return !editorsLogins.isEmpty();
        }

        return true;
    }

    @Override
    public Task deleteTask(Long taskId, int version) {
        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID " + taskId)
        );

        if (task.getVersion() != version) {
            throw new OldEntityVersionException("Есть более новая версия задачи с ID: " + task.getId());
        }

        taskRepository.delete(task);

        return task;
    }
}
