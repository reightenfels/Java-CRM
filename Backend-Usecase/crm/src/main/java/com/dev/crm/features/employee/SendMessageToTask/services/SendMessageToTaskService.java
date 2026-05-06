package com.dev.crm.features.employee.SendMessageToTask.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.TaskDialog;
import com.dev.crm.entities.TaskDialogMessage;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.SendMessageToTask.contracts.SendMessageToTaskContract;
import com.dev.crm.repositories.TaskDialogMessagesRepository;

@Service
public class SendMessageToTaskService implements SendMessageToTaskContract {
    private final TaskDialogMessagesRepository messagesRepository;

    public SendMessageToTaskService(TaskDialogMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public Long createMessage(
        String text,
        Long senderId,
        Long dialogId,
        LocalDateTime createdAt
    ) {
        User sender = new User();
        sender.setId(senderId);

        TaskDialog dialog = new TaskDialog();
        dialog.setId(dialogId);

        TaskDialogMessage message = new TaskDialogMessage();
        message.setText(text);
        message.setSender(sender);
        message.setDialog(dialog);
        message.setCreatedAt(createdAt);

        messagesRepository.save(message);

        return message.getId();
    }
}
