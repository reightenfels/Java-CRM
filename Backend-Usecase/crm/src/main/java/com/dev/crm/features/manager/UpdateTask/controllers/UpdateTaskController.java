package com.dev.crm.features.manager.UpdateTask.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.UpdateTask.UpdateTaskUseCase;
import com.dev.crm.features.manager.UpdateTask.dto.UpdateTaskRequest;
import com.dev.crm.features.manager.UpdateTask.dto.UpdateTaskResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks/update")
public class UpdateTaskController {
    private final UpdateTaskUseCase useCase;

    public UpdateTaskController(UpdateTaskUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ApiResponse<UpdateTaskResponse> update(@RequestBody @Valid UpdateTaskRequest request) {
        UpdateTaskResponse useCaseResult = useCase.handle(request);

        return ApiResponse.ok(useCaseResult);
    }
}
