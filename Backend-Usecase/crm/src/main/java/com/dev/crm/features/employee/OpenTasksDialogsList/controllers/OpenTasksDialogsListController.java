package com.dev.crm.features.employee.OpenTasksDialogsList.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.OpenTasksDialogsList.OpenTasksDialogsListUseCase;
import com.dev.crm.features.employee.OpenTasksDialogsList.dto.OpenTasksDialogsListResponse;

@RestController
@RequestMapping("/api/employee/dialogs/tasks")
public class OpenTasksDialogsListController {
    private final OpenTasksDialogsListUseCase useCase;

    public OpenTasksDialogsListController(OpenTasksDialogsListUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ApiResponse<OpenTasksDialogsListResponse> openDialogsList() {
        Long currentAuthUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        OpenTasksDialogsListResponse useCaseResult = useCase.handle(currentAuthUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
