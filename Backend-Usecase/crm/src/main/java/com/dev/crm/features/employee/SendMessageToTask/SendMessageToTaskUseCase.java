package com.dev.crm.features.employee.SendMessageToTask;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.features.employee.SendMessageToTask.contracts.SendMessageToTaskContract;
import com.dev.crm.features.employee.SendMessageToTask.dto.SendMessageToTaskRequest;
import com.dev.crm.features.employee.SendMessageToTask.dto.SendMessageToTaskResponse;
import com.dev.crm.queues.dto.notifications.SendMessageToTask;
import com.dev.crm.queues.producers.NotificationProducer;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SendMessageToTaskUseCase {
    private final SendMessageToTaskContract sendMessageService;
    private final NotificationProducer notificationProducer;

    public SendMessageToTaskUseCase(
        SendMessageToTaskContract sendMessageService,
        NotificationProducer notificationProducer
    ) {
        this.sendMessageService = sendMessageService;
        this.notificationProducer = notificationProducer;
    }

    @Transactional
    public SendMessageToTaskResponse handle(
        SendMessageToTaskRequest request,
        Long currentUserId
    ) {
        LocalDateTime sendingDateTime = LocalDateTime.now();

        Long messageId = sendMessageService.createMessage(
            request.getText(), 
            request.getDialogId(), 
            currentUserId, 
            sendingDateTime
        );

        notificationProducer.send(
            SendMessageToTask.builder()
            .type("SendMessageToTask")
            .timestamp(System.currentTimeMillis())
            .text(request.getText())
            .messageId(messageId)
            .sendingDateTime(sendingDateTime)
            .senderId(currentUserId)
            .dialogId(request.getDialogId())
            .build()
        );

        return new SendMessageToTaskResponse(
            messageId
        );
    }
}
