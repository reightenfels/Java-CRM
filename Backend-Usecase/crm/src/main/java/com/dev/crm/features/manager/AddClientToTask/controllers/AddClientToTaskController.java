package com.dev.crm.features.manager.AddClientToTask.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.AddClientToTask.AddClientToTaskUseCase;
import com.dev.crm.features.manager.AddClientToTask.dto.AddClientToTaskRequest;
import com.dev.crm.features.manager.AddClientToTask.dto.AddClientToTaskResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks/add-client")
public class AddClientToTaskController {
    private final AddClientToTaskUseCase useCase;

    public AddClientToTaskController(AddClientToTaskUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/")
    public ApiResponse<AddClientToTaskResponse> addClient(@RequestBody @Valid AddClientToTaskRequest request) {
        AddClientToTaskResponse useCaseResult = useCase.handle(request);

        return ApiResponse.ok(useCaseResult);
    }
}
