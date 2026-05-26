package com.dev.crm.features.manager.CreateTask.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.CreateTask.CreateTaskUseCase;
import com.dev.crm.features.manager.CreateTask.dto.CreateTaskRequest;
import com.dev.crm.features.manager.CreateTask.dto.CreateTaskResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks/create")
public class CreateTaskController {
    private final CreateTaskUseCase useCase;

    public CreateTaskController(CreateTaskUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ApiResponse<CreateTaskResponse> create(@RequestBody @Valid CreateTaskRequest request) {
        CreateTaskResponse useCaseResult = useCase.handle(request);
        return ApiResponse.ok(useCaseResult);
    }
}
