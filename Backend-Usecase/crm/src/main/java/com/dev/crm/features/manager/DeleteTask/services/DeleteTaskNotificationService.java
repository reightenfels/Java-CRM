package com.dev.crm.features.manager.DeleteTask.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.queues.dto.notifications.DeleteTask;
import com.dev.crm.queues.dto.notifications.DeleteTaskDialog;
import com.dev.crm.queues.producers.NotificationProducer;

@Service
public class DeleteTaskNotificationService {
    private final NotificationProducer notificationProducer;

    public DeleteTaskNotificationService(
        NotificationProducer notificationProducer
    ) {
        this.notificationProducer = notificationProducer;
    }

    private void sendDeleteTask(Long deletedTaskId) {
        notificationProducer.send(
            DeleteTask.builder()
            .type("DeleteTask")
            .timestamp(System.currentTimeMillis())
            .deletedTaskId(deletedTaskId)
            .build()
        );
    }

    private void sendDeleteTaskDialog(Long deletedDialogId) {
        notificationProducer.send(
            DeleteTaskDialog.builder()
            .type("DeleteTaskDialog")
            .timestamp(System.currentTimeMillis())
            .deletedDialogId(deletedDialogId)
            .build()
        );
    }

    @Transactional
    public void send(Long deletedTaskId, Long deletedDialogId) {
        sendDeleteTask(deletedTaskId);
        sendDeleteTaskDialog(deletedDialogId);
    }
}
