package com.dev.crm.features.employee.OpenTaskDialog.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.employee.OpenTaskDialog.OpenTaskDialogUseCase;
import com.dev.crm.features.employee.OpenTaskDialog.dto.OpenTaskDialogResponse;

@RestController
@RequestMapping("/api/dialogs/tasks/{id}")
public class OpenTaskDialogController {
    private final OpenTaskDialogUseCase useCase;

    public OpenTaskDialogController(OpenTaskDialogUseCase useCase) {
        this.useCase = useCase;
    }

    public ApiResponse<OpenTaskDialogResponse> openDialog(@PathVariable("id") Long dialogId) {
        OpenTaskDialogResponse useCaseResult = useCase.handle(dialogId);

        return ApiResponse.ok(useCaseResult);
    }
}
