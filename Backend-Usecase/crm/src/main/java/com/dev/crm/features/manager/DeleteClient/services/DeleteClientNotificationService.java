package com.dev.crm.features.manager.DeleteClient.services;

import org.springframework.stereotype.Service;

import com.dev.crm.queues.dto.notifications.DeleteClient;
import com.dev.crm.queues.producers.NotificationProducer;

@Service
public class DeleteClientNotificationService {
    private final NotificationProducer notificationProducer;

    public DeleteClientNotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void send(Long deletedClientId) {
        notificationProducer.send(
            DeleteClient.builder()
            .type("DeleteClient")
            .timestamp(System.currentTimeMillis())
            .deletedClientId(deletedClientId)
            .build()
        );
    }
}
