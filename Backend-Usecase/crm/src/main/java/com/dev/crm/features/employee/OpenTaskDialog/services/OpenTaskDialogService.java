package com.dev.crm.features.employee.OpenTaskDialog.services;

import java.util.List;

import com.dev.crm.entities.TaskDialogMessage;
import com.dev.crm.features.employee.OpenTaskDialog.contracts.OpenTaskDialogContract;
import com.dev.crm.repositories.TaskDialogMessagesRepository;

public class OpenTaskDialogService implements OpenTaskDialogContract {
    private final TaskDialogMessagesRepository messagesRepository;

    public OpenTaskDialogService(TaskDialogMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<TaskDialogMessage> getAllDialogMessages(Long dialogId) {
        return messagesRepository.findByDialogId(dialogId);
    }
}
