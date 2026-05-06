package com.dev.crm.features.employee.OpenTaskDialog;

import java.util.List;

import com.dev.crm.features.employee.OpenTaskDialog.dto.OpenTaskDialogResponse;

import jakarta.transaction.Transactional;

import com.dev.crm.features.employee.OpenTaskDialog.contracts.OpenTaskDialogContract;

import com.dev.crm.entities.TaskDialogMessage;

public class OpenTaskDialogUseCase {
    private final OpenTaskDialogContract openTaskDialogService;

    public OpenTaskDialogUseCase(OpenTaskDialogContract openTaskDialogService) {
        this.openTaskDialogService = openTaskDialogService;
    }

    @Transactional
    public OpenTaskDialogResponse handle(Long dialogId) {
        List<TaskDialogMessage> messages = openTaskDialogService.getAllDialogMessages(dialogId);

        return new OpenTaskDialogResponse (
            messages
        );
    }
}
