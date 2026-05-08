package com.dev.crm.features.manager.CreateTask.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.entities.Task;
import com.dev.crm.entities.TaskDialog;
import com.dev.crm.queues.dto.notifications.CreateTask;
import com.dev.crm.queues.dto.notifications.CreateTaskDialog;
import com.dev.crm.queues.producers.NotificationProducer;
import com.dev.crm.repositories.TaskDialogRepository;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class CreateTaskNotificationService {
    private final NotificationProducer notificationProducer;
    private final TaskRepository taskRepository;
    private final TaskDialogRepository dialogRepository;

    public CreateTaskNotificationService(
        NotificationProducer notificationProducer,
        TaskRepository taskRepository,
        TaskDialogRepository dialogRepository
    ) {
        this.notificationProducer = notificationProducer;
        this.taskRepository = taskRepository;
        this.dialogRepository = dialogRepository;
    }

    private void sendNewTask(Long newTaskId) {
        Task newTask = taskRepository.findById(newTaskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + newTaskId)
        );

        notificationProducer.send(
            CreateTask.builder()
            .type("CreateTask")
            .timestamp(System.currentTimeMillis())
            .newTask(newTask)
            .build()
        );
    }

    private void sendNewTaskDialog(Long taskId, Long dialogId) {
        TaskDialog dialog = dialogRepository.findById(dialogId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден диалог по задаче с ID: " + dialogId)
        );

        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Не найдена задача с ID: " + taskId)
        );

        notificationProducer.send(
            CreateTaskDialog.builder()
            .type("CreateTaskDialog")
            .timestamp(System.currentTimeMillis())
            .taskName(task.getTitle())
            .taskId(taskId)
            .dialogId(dialog.getId())
            .build()
        );
    }

    @Transactional
    public void send(Long newTaskId, Long newDialogId) {
        sendNewTask(newTaskId);
        sendNewTaskDialog(newTaskId, newDialogId);
    }
}
