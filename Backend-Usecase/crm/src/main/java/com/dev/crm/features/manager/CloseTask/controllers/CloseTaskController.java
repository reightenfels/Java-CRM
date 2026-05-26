package com.dev.crm.features.manager.CloseTask.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.CloseTask.CloseTaskUseCase;
import com.dev.crm.features.manager.CloseTask.dto.CloseTaskRequest;
import com.dev.crm.features.manager.CloseTask.dto.CloseTaskResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks/close")
public class CloseTaskController {
    private final CloseTaskUseCase useCase;

    public CloseTaskController(CloseTaskUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ApiResponse<CloseTaskResponse> close(@RequestBody @Valid CloseTaskRequest request) {
        Long currentAuthUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        CloseTaskResponse useCaseResult = useCase.handle(request, currentAuthUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
