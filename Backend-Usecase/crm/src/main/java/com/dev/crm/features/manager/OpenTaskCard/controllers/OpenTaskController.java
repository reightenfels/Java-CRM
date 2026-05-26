package com.dev.crm.features.manager.OpenTaskCard.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.OpenTaskCard.OpenTaskUseCase;
import com.dev.crm.features.manager.OpenTaskCard.dto.OpenTaskResponse;

@RestController
@RequestMapping("/api/tasks")
public class OpenTaskController {
    private final OpenTaskUseCase useCase;

    public OpenTaskController(OpenTaskUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public ApiResponse<OpenTaskResponse> get(@PathVariable("id") Long dialogId) {
        Long currentAuthUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        OpenTaskResponse useCaseResult = useCase.handle(dialogId, currentAuthUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
