package com.dev.crm.features.employee.OpenTaskDialog.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.OpenTaskDialog.OpenTaskDialogUseCase;
import com.dev.crm.features.employee.OpenTaskDialog.dto.OpenTaskDialogResponse;

@RestController
@RequestMapping("/api/dialogs/tasks")
public class OpenTaskDialogController {
    private final OpenTaskDialogUseCase useCase;

    public OpenTaskDialogController(OpenTaskDialogUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public ApiResponse<OpenTaskDialogResponse> openDialog(@PathVariable("id") Long dialogId) {
        Long currentUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        OpenTaskDialogResponse useCaseResult = useCase.handle(dialogId, currentUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
