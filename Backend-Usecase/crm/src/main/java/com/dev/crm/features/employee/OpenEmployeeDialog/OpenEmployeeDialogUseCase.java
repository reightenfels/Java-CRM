package com.dev.crm.features.employee.OpenEmployeeDialog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.PersonalEmployeeDialogMessage;
import com.dev.crm.features.employee.OpenEmployeeDialog.contracts.OpenEmployeeDialogContract;
import com.dev.crm.features.employee.OpenEmployeeDialog.dto.OpenEmployeeDialogResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenEmployeeDialogUseCase {
    private final OpenEmployeeDialogContract openEmployeeDialogService;

    public OpenEmployeeDialogUseCase(OpenEmployeeDialogContract openEmployeeDialogService) {
        this.openEmployeeDialogService = openEmployeeDialogService;
    }

    @Transactional
    public OpenEmployeeDialogResponse handle(Long dialogId) {
        List<PersonalEmployeeDialogMessage> messages = openEmployeeDialogService.getAllDialogMessages(dialogId);

        return new OpenEmployeeDialogResponse(
            messages
        );
    }
}
