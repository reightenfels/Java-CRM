package com.dev.crm.features.employee.OpenEmployeeDialogsList.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.OpenEmployeeDialogsList.OpenEmployeeDialogsListUseCase;
import com.dev.crm.features.employee.OpenEmployeeDialogsList.dto.OpenEmployeeDialogsListResponse;

@RestController
@RequestMapping("/api/dialogs/employees/list")
public class OpenEmployeeDialogsListController {
    private final OpenEmployeeDialogsListUseCase useCase;

    public OpenEmployeeDialogsListController(OpenEmployeeDialogsListUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/")
    public ApiResponse<OpenEmployeeDialogsListResponse> openList() {
        Long currentAuthUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        OpenEmployeeDialogsListResponse useCaseResult = useCase.handle(currentAuthUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
