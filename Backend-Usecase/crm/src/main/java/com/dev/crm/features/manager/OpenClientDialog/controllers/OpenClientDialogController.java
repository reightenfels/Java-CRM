package com.dev.crm.features.manager.OpenClientDialog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.OpenClientDialog.OpenClientDialogUseCase;
import com.dev.crm.features.manager.OpenClientDialog.dto.OpenClientDialogResponse;

@RestController
@RequestMapping("/api/dialogs/clients/{id}")
public class OpenClientDialogController {
    private final OpenClientDialogUseCase useCase;

    public OpenClientDialogController(OpenClientDialogUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/")
    public ApiResponse<OpenClientDialogResponse> get(@PathVariable("id") Long dialogId) {
        OpenClientDialogResponse useCaseResult = useCase.handle(dialogId);

        return ApiResponse.ok(useCaseResult);
    }
}
