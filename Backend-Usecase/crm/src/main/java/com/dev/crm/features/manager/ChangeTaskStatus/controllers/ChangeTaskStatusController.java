package com.dev.crm.features.manager.ChangeTaskStatus.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.ChangeTaskStatus.ChangeTaskStatusUseCase;
import com.dev.crm.features.manager.ChangeTaskStatus.dto.ChangeTaskStatusRequest;
import com.dev.crm.features.manager.ChangeTaskStatus.dto.ChangeTaskStatusResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks/change-status")
public class ChangeTaskStatusController {
    private final ChangeTaskStatusUseCase useCase;

    public ChangeTaskStatusController(ChangeTaskStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ApiResponse<ChangeTaskStatusResponse> changeStatus(@RequestBody @Valid ChangeTaskStatusRequest request) {
        ChangeTaskStatusResponse useCaseResult = useCase.handle(request);

        return ApiResponse.ok(useCaseResult);
    }
}
