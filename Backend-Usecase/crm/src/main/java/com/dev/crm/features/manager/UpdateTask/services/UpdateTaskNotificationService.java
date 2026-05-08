package com.dev.crm.features.manager.UpdateTask.services;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Task;
import com.dev.crm.queues.dto.notifications.UpdateTask;
import com.dev.crm.queues.producers.NotificationProducer;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class UpdateTaskNotificationService {
    private final NotificationProducer notificationProducer;
    private final TaskRepository taskRepository;

    public UpdateTaskNotificationService(
        NotificationProducer notificationProducer,
        TaskRepository taskRepository
    ) {
        this.notificationProducer = notificationProducer;
        this.taskRepository = taskRepository;
    }

    public void send(Long updatedTaskId) {
        Task updatedTask = taskRepository.findById(updatedTaskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + updatedTaskId)
        );

        notificationProducer.send(
            UpdateTask.builder()
            .type("UpdateTask")
            .timestamp(System.currentTimeMillis())
            .updatedTask(updatedTask)
            .build()
        );
    }
}
