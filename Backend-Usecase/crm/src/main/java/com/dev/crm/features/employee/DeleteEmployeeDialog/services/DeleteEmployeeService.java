package com.dev.crm.features.employee.DeleteEmployeeDialog.services;

import org.springframework.stereotype.Service;

import com.dev.crm.features.employee.DeleteEmployeeDialog.contracts.DeleteEmployeeContract;
import com.dev.crm.repositories.EmployeeDialogMessagesRepository;
import com.dev.crm.repositories.EmployeeDialogRepository;

@Service
public class DeleteEmployeeService implements DeleteEmployeeContract {
    private final EmployeeDialogRepository dialogRepository;
    private final EmployeeDialogMessagesRepository messagesRepository;

    public DeleteEmployeeService(
        EmployeeDialogRepository dialogRepository,
        EmployeeDialogMessagesRepository messagesRepository
    ) {
        this.dialogRepository = dialogRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public Long deleteDialog(Long dialogId) {
        dialogRepository.deleteById(dialogId);
        
        return dialogId;
    }

    @Override
    public Long deleteAllDialogMessages(Long dialogId) {
        return messagesRepository.deleteByDialogId(dialogId);
    }
}
