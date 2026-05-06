package com.dev.crm.features.employee.SendMessageToTask.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.SendMessageToTask.SendMessageToTaskUseCase;
import com.dev.crm.features.employee.SendMessageToTask.dto.SendMessageToTaskRequest;
import com.dev.crm.features.employee.SendMessageToTask.dto.SendMessageToTaskResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages/tasks/send")
public class SendMessageToTaskController {
    private final SendMessageToTaskUseCase useCase;

    public SendMessageToTaskController(SendMessageToTaskUseCase useCase) {
        this.useCase = useCase;
    }

    public ApiResponse<SendMessageToTaskResponse> send(@RequestBody @Valid SendMessageToTaskRequest request) {
        Long currentUserAuthId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SendMessageToTaskResponse useCaseResult = useCase.handle(request, currentUserAuthId);

        return ApiResponse.ok(useCaseResult);
    }
}
