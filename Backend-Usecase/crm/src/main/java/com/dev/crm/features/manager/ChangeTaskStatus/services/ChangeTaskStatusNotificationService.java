package com.dev.crm.features.manager.ChangeTaskStatus.services;

import org.springframework.stereotype.Service;

import com.dev.crm.queues.dto.notifications.ChangeTaskStatus;
import com.dev.crm.queues.producers.NotificationProducer;

@Service
public class ChangeTaskStatusNotificationService {
    private final NotificationProducer notificationProducer;

    public ChangeTaskStatusNotificationService(
        NotificationProducer notificationProducer
    ) {
        this.notificationProducer = notificationProducer;
    }

    public void send(
        Long taskId,
        String newStatusSystemName
    ) {
        notificationProducer.send(
            ChangeTaskStatus.builder()
            .type("ChangeTaskStatus")
            .timestamp(System.currentTimeMillis())
            .taskId(taskId)
            .newStatusSystemName(newStatusSystemName)
            .build()
        );
    }
}
