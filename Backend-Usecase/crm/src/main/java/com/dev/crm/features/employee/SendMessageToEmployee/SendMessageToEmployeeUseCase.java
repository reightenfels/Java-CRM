package com.dev.crm.features.employee.SendMessageToEmployee;

import java.time.LocalDateTime;

import com.dev.crm.features.employee.SendMessageToEmployee.contracts.SendMessageToEmployeeContract;
import com.dev.crm.features.employee.SendMessageToEmployee.dto.SendMessageToEmployeeRequest;
import com.dev.crm.features.employee.SendMessageToEmployee.dto.SendMessageToEmployeeResponse;

import jakarta.transaction.Transactional;

public class SendMessageToEmployeeUseCase {
    private final SendMessageToEmployeeContract sendMessageService;

    public SendMessageToEmployeeUseCase(SendMessageToEmployeeContract sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Transactional
    public SendMessageToEmployeeResponse handle(SendMessageToEmployeeRequest request, Long currentUserId) {
        Long dialogId = request.getDialogId();

        if (dialogId == null) {
            dialogId = sendMessageService.createDialog(currentUserId, request.getRecipientId(), null);
        }
        else if (!sendMessageService.isDialogExists(dialogId)) {
            dialogId = sendMessageService.createDialog(currentUserId, request.getRecipientId(), null);
        }

        Long messageId = sendMessageService.createMessage(request.getText(), currentUserId, dialogId, LocalDateTime.now());

        // TODO: Отправить событие в очередь 

        return new SendMessageToEmployeeResponse(
            messageId,
            dialogId
        );
    }
}
