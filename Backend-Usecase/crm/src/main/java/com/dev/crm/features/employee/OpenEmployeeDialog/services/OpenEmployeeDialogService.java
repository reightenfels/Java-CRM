package com.dev.crm.features.employee.OpenEmployeeDialog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.PersonalEmployeeDialogMessage;
import com.dev.crm.features.employee.OpenEmployeeDialog.contracts.OpenEmployeeDialogContract;
import com.dev.crm.repositories.EmployeeDialogMessagesRepository;

@Service
public class OpenEmployeeDialogService implements OpenEmployeeDialogContract {
    private final EmployeeDialogMessagesRepository messagesRepository;

    public OpenEmployeeDialogService(EmployeeDialogMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<PersonalEmployeeDialogMessage> getAllDialogMessages(Long dialogId) {
        return messagesRepository.findByDialogId(dialogId);
    }
}
