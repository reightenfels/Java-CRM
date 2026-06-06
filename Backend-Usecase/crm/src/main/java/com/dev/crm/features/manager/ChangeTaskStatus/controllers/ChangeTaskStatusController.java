package com.dev.crm.features.manager.ChangeTaskStatus.controllers;

import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/tasks")
public class ChangeTaskStatusController {
    private final ChangeTaskStatusUseCase useCase;

    public ChangeTaskStatusController(ChangeTaskStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{id}/status")
    public ApiResponse<ChangeTaskStatusResponse> changeStatus(
        @PathVariable("id") Long taskId,
        @RequestBody @Valid ChangeTaskStatusRequest request
    ) {
        ChangeTaskStatusResponse useCaseResult = useCase.handle(taskId, request);

        return ApiResponse.ok(useCaseResult);
    }
}
