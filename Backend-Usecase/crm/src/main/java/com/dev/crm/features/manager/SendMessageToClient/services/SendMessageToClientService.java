package com.dev.crm.features.manager.SendMessageToClient.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.ClientDialog;
import com.dev.crm.entities.ClientDialogMessage;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.SendMessageToClient.contracts.SendMessageToClientContract;
import com.dev.crm.repositories.ClientDialogMessagesRepository;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;

@Service
public class SendMessageToClientService implements SendMessageToClientContract {
    private final ClientDialogMessagesRepository messagesRepository;

    public SendMessageToClientService(ClientDialogMessagesRepository messagesRepository) {
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

        ClientDialog dialog = new ClientDialog();
        dialog.setId(dialogId);

        ClientDialogMessage message = new ClientDialogMessage();
        message.setText(text);
        message.setSender(sender);
        message.setDialog(dialog);
        message.setCreatedAt(createdAt);
        
        try {
            messagesRepository.save(message);
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка при создании сообщения в диалоге с клиентом: " + e.getMessage());
        }

        return message.getId();
    }
}
