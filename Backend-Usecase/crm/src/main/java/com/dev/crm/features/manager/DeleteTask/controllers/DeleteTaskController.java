package com.dev.crm.features.manager.DeleteTask.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.DeleteTask.DeleteTaskUseCase;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskRequest;
import com.dev.crm.features.manager.DeleteTask.dto.DeleteTaskResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks/{id}")
public class DeleteTaskController {
    private final DeleteTaskUseCase useCase;

    public DeleteTaskController(DeleteTaskUseCase useCase) {
        this.useCase = useCase;
    }

    @DeleteMapping
    public ApiResponse<DeleteTaskResponse> delete(
        @PathVariable("id") Long taskId,
        @RequestBody @Valid DeleteTaskRequest request
    ) {
        Long currentAuthUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        DeleteTaskResponse useCaseResult = useCase.handle(taskId, request, currentAuthUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
