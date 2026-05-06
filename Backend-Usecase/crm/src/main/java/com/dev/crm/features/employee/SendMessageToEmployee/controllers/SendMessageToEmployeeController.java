package com.dev.crm.features.employee.SendMessageToEmployee.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.SendMessageToEmployee.SendMessageToEmployeeUseCase;
import com.dev.crm.features.employee.SendMessageToEmployee.dto.SendMessageToEmployeeRequest;
import com.dev.crm.features.employee.SendMessageToEmployee.dto.SendMessageToEmployeeResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages/employees/send")
public class SendMessageToEmployeeController {
    private final SendMessageToEmployeeUseCase useCase;

    public SendMessageToEmployeeController(SendMessageToEmployeeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/")
    public ApiResponse<SendMessageToEmployeeResponse> send(@RequestBody @Valid SendMessageToEmployeeRequest request) {
        Long currentUserAuthId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SendMessageToEmployeeResponse useCaseResult = useCase.handle(request, currentUserAuthId);

        return ApiResponse.ok(useCaseResult);
    }
}
