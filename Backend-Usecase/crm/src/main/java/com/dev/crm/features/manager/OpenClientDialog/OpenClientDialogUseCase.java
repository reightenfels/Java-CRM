package com.dev.crm.features.manager.OpenClientDialog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.ClientDialogMessage;
import com.dev.crm.features.manager.OpenClientDialog.contracts.OpenClientDialogContract;
import com.dev.crm.features.manager.OpenClientDialog.dto.OpenClientDialogResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenClientDialogUseCase {
    private final OpenClientDialogContract openClientDialogService;

    public OpenClientDialogUseCase(OpenClientDialogContract openClientDialogService) {
        this.openClientDialogService = openClientDialogService;
    }

    @Transactional
    public OpenClientDialogResponse handle(Long dialogId) {
        List<ClientDialogMessage> messages = openClientDialogService.getAllDialogMessages(dialogId);

        return new OpenClientDialogResponse(
            messages
        );
    }
}
