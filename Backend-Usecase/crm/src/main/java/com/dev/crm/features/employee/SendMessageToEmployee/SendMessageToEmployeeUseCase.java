package com.dev.crm.features.employee.SendMessageToEmployee;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.employee.SendMessageToEmployee.contracts.SendMessageToEmployeeContract;
import com.dev.crm.features.employee.SendMessageToEmployee.dto.SendMessageToEmployeeRequest;
import com.dev.crm.features.employee.SendMessageToEmployee.dto.SendMessageToEmployeeResponse;
import com.dev.crm.queues.dto.notifications.SendMessageToEmployee;
import com.dev.crm.queues.producers.NotificationProducer;

@Service
public class SendMessageToEmployeeUseCase {
    private final SendMessageToEmployeeContract sendMessageService;
    private final NotificationProducer notificationProducer;

    public SendMessageToEmployeeUseCase(
        SendMessageToEmployeeContract sendMessageService,
        NotificationProducer notificationProducer
    ) {
        this.sendMessageService = sendMessageService;
        this.notificationProducer = notificationProducer;
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

        LocalDateTime sendingDateTime = LocalDateTime.now();

        Long messageId = sendMessageService.createMessage(request.getText(), currentUserId, dialogId, sendingDateTime);

        notificationProducer.send(
            SendMessageToEmployee.builder()
            .type("SendMessageToEmployee")
            .timestamp(System.currentTimeMillis())
            .text(request.getText())
            .messageId(messageId)
            .sendingDateTime(sendingDateTime)
            .senderId(currentUserId)
            .dialogId(dialogId)
            .build()
        ); 

        return new SendMessageToEmployeeResponse(
            messageId,
            dialogId
        );
    }
}
