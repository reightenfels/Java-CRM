package com.dev.crm.features.employee.GetTasksList.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.GetTasksList.GetTasksListUseCase;
import com.dev.crm.features.employee.GetTasksList.dto.GetTasksListResponse;

@RestController
@RequestMapping("/api/employee/tasks")
public class GetTasksListController {
    private final GetTasksListUseCase useCase;

    public GetTasksListController(GetTasksListUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ApiResponse<GetTasksListResponse> getTasks() {
        Long currentUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        GetTasksListResponse useCaseResult = useCase.handle(currentUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
