package com.dev.crm.features.manager.OpenClientDialog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.ClientDialogMessage;
import com.dev.crm.features.manager.OpenClientDialog.contracts.OpenClientDialogContract;
import com.dev.crm.repositories.ClientDialogMessagesRepository;

@Service
public class OpenClientDialogService implements OpenClientDialogContract {
    private final ClientDialogMessagesRepository messagesRepository;

    public OpenClientDialogService(ClientDialogMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<ClientDialogMessage> getAllDialogMessages(Long dialogId) {
        return messagesRepository.findByDialogId(dialogId);
    }
}
