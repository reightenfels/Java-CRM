package com.dev.crm.features.manager.SendMessageToClient;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.SendMessageToClient.contracts.SendMessageToClientContract;
import com.dev.crm.features.manager.SendMessageToClient.dto.SendMessageToClientRequest;
import com.dev.crm.features.manager.SendMessageToClient.dto.SendMessageToClientResponse;
import com.dev.crm.features.manager.SendMessageToClient.services.SendMessageToClientNotificationService;

@Service
public class SendMessageToClientUseCase {
    private final SendMessageToClientContract sendMessageService;
    private final SendMessageToClientNotificationService notificationService;

    public SendMessageToClientUseCase(
        SendMessageToClientContract sendMessageService,
        SendMessageToClientNotificationService notificationService
    ) {
        this.sendMessageService = sendMessageService;
        this.notificationService = notificationService;
    }

    @Transactional
    public SendMessageToClientResponse handle(SendMessageToClientRequest request, Long currentUserId) {
        LocalDateTime sendingDateTime = LocalDateTime.now();
        
        Long newMessageId = sendMessageService.createMessage(
            request.getText(),
            currentUserId,
            request.getDialogId(),
            sendingDateTime
        );

        notificationService.send(
            request.getText(),
            newMessageId,
            sendingDateTime,
            currentUserId,
            request.getDialogId()
        );

        return new SendMessageToClientResponse(
            newMessageId
        );
    }
}
