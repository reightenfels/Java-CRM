package com.dev.crm.features.manager.SendMessageToClient.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.queues.dto.notifications.SendMessageToClient;
import com.dev.crm.queues.producers.NotificationProducer;

@Service
public class SendMessageToClientNotificationService {
    private final NotificationProducer notificationProducer;

    public SendMessageToClientNotificationService(
        NotificationProducer notificationProducer
    ) {
        this.notificationProducer = notificationProducer;
    }

    public void send(
        String text,
        Long messageId,
        LocalDateTime sendingDateTime,
        Long senderId,
        Long dialogId
    ) {
        notificationProducer.send(
            SendMessageToClient.builder()
            .type("SendMessageToClient")
            .timestamp(System.currentTimeMillis())
            .text(text)
            .messageId(messageId)
            .sendingDateTime(sendingDateTime)
            .senderId(senderId)
            .dialogId(dialogId)
            .build()
        );
    }
}
