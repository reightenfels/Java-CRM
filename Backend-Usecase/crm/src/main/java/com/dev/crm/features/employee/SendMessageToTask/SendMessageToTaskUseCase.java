package com.dev.crm.features.employee.SendMessageToTask;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.features.employee.SendMessageToTask.contracts.SendMessageToTaskContract;
import com.dev.crm.features.employee.SendMessageToTask.dto.SendMessageToTaskRequest;
import com.dev.crm.features.employee.SendMessageToTask.dto.SendMessageToTaskResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SendMessageToTaskUseCase {
    private final SendMessageToTaskContract sendMessageService;

    public SendMessageToTaskUseCase(SendMessageToTaskContract sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Transactional
    public SendMessageToTaskResponse handle(
        SendMessageToTaskRequest request,
        Long currentUserId
    ) {
        Long messageId = sendMessageService.createMessage(
            request.getText(), 
            request.getDialogId(), 
            currentUserId, 
            LocalDateTime.now()
        );

        // TODO: Отправить событие в очередь

        return new SendMessageToTaskResponse(
            messageId
        );
    }
}
