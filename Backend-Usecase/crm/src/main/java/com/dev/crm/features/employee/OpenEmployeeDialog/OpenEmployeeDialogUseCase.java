package com.dev.crm.features.employee.OpenEmployeeDialog;

import java.util.List;

import com.dev.crm.entities.PersonalEmployeeDialogMessage;
import com.dev.crm.features.employee.OpenEmployeeDialog.contracts.OpenEmployeeDialogContract;
import com.dev.crm.features.employee.OpenEmployeeDialog.dto.OpenEmployeeDialogResponse;

import jakarta.transaction.Transactional;

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
