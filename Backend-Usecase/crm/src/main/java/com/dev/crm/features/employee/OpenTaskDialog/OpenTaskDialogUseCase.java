package com.dev.crm.features.employee.OpenTaskDialog;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.employee.OpenTaskDialog.contracts.OpenTaskDialogContract;
import com.dev.crm.features.employee.OpenTaskDialog.dto.OpenTaskDialogMessageDTO;
import com.dev.crm.features.employee.OpenTaskDialog.dto.OpenTaskDialogResponse;
@Service
public class OpenTaskDialogUseCase {
    private final OpenTaskDialogContract openTaskDialogService;

    public OpenTaskDialogUseCase(OpenTaskDialogContract openTaskDialogService) {
        this.openTaskDialogService = openTaskDialogService;
    }

    @Transactional
    public OpenTaskDialogResponse handle(Long dialogId, Long currentUserId) {
        List<OpenTaskDialogMessageDTO> messages = openTaskDialogService.getAllDialogMessages(dialogId, currentUserId);

        return new OpenTaskDialogResponse (
            messages
        );
    }
}
