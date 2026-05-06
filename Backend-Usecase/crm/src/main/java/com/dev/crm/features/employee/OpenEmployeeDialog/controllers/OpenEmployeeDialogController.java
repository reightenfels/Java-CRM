package com.dev.crm.features.employee.OpenEmployeeDialog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.employee.OpenEmployeeDialog.OpenEmployeeDialogUseCase;
import com.dev.crm.features.employee.OpenEmployeeDialog.dto.OpenEmployeeDialogResponse;


@RestController
@RequestMapping("/api/dialogs/employees/{id}")
public class OpenEmployeeDialogController {
    private final OpenEmployeeDialogUseCase useCase;

    public OpenEmployeeDialogController(OpenEmployeeDialogUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/")
    public ApiResponse<OpenEmployeeDialogResponse> openDialog(@PathVariable("id") Long dialogId) {
        OpenEmployeeDialogResponse useCaseResult = useCase.handle(dialogId);

        return ApiResponse.ok(useCaseResult);
    }
}
