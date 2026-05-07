package com.dev.crm.features.manager.SendMessageToClient;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.SendMessageToClient.contracts.SendMessageToClientContract;
import com.dev.crm.features.manager.SendMessageToClient.dto.SendMessageToClientRequest;
import com.dev.crm.features.manager.SendMessageToClient.dto.SendMessageToClientResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SendMessageToClientUseCase {
    private final SendMessageToClientContract sendMessageService;

    public SendMessageToClientUseCase(SendMessageToClientContract sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Transactional
    public SendMessageToClientResponse handle(SendMessageToClientRequest request, Long currentUserId) {
        Long newMessageId = sendMessageService.createMessage(
            request.getText(),
            currentUserId,
            request.getDialogId(),
            LocalDateTime.now()
        );

        // TODO: Отправить задачу в очередь

        return new SendMessageToClientResponse(
            newMessageId
        );
    }
}
