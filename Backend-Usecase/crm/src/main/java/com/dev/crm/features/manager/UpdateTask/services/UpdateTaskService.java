package com.dev.crm.features.manager.UpdateTask.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.entities.Task;
import com.dev.crm.entities.TaskStatus;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.UpdateTask.contracts.UpdateTaskContract;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.repositories.TaskStatusRepository;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;

import jakarta.persistence.OptimisticLockException;

@Service
public class UpdateTaskService implements UpdateTaskContract {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository statusRepository;
    private final UserRepository userRepository;

    public UpdateTaskService(
        TaskRepository taskRepository,
        TaskStatusRepository statusRepository,
        UserRepository userRepository
    ) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Long updateTask(
        Long id,
        String title,
        Long price,
        LocalDateTime deadline,
        Long statusId,
        Long responsibleId,
        Long clientId,
        int version
    ) {
        Task task = taskRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + id)
        );
        task.setVersion(version);
        task.setTitle(title);
        task.setPrice(price);
        task.setDeadline(deadline);

        TaskStatus status = statusRepository.findById(statusId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден статус с ID: " + statusId)
        );
        task.setStatus(status);

        User responsible = userRepository.findById(responsibleId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден ответственный с ID: " + responsibleId)
        );
        task.setResponsible(responsible);

        User client = userRepository.findById(clientId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден клиент с ID: " + clientId)
        );
        task.setClient(client);

        try {
            task = taskRepository.save(task);
            return task.getId();
        } 
        catch (OptimisticLockException e) {
            throw new OldEntityVersionException("Есть более новая версия данной задачи");
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка обновления задачи: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Long updateTaskPerformers(
        Long taskId, 
        Set<Long> performersIds,
        int version
    ) {
        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + taskId)
        );
        task.setVersion(version);
        
        Set<User> currentSet = new HashSet<>(task.getPerformers());
        Set<User> desiredSet = new HashSet<>(userRepository.findAllById(performersIds));

        Set<User> toRemove = new HashSet<>(currentSet);
        toRemove.removeAll(desiredSet);
        Set<User> toAdd = new HashSet<>(desiredSet);
        toAdd.removeAll(currentSet);

        task.getPerformers().removeAll(toRemove);
        task.getPerformers().addAll(toAdd);

        task = taskRepository.save(task);
        
        return task.getId();
    }
}
