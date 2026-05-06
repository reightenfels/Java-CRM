package com.dev.crm.features.employee.DeleteEmployeeDialog.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.employee.DeleteEmployeeDialog.DeleteEmployeeDialogUseCase;
import com.dev.crm.features.employee.DeleteEmployeeDialog.dto.DeleteEmployeeDialogRequest;
import com.dev.crm.features.employee.DeleteEmployeeDialog.dto.DeleteEmployeeDialogResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dialogs/employees/delete")
public class DeleteEmployeeDialogController {
    private final DeleteEmployeeDialogUseCase useCase;

    public DeleteEmployeeDialogController(DeleteEmployeeDialogUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/")
    public ApiResponse<DeleteEmployeeDialogResponse> delete(@RequestBody @Valid DeleteEmployeeDialogRequest request) {
        DeleteEmployeeDialogResponse useCaseResult = useCase.handle(request);

        return ApiResponse.ok(useCaseResult);
    }
}
