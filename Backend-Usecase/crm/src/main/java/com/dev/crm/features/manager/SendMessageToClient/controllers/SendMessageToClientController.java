package com.dev.crm.features.manager.SendMessageToClient.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.SendMessageToClient.SendMessageToClientUseCase;
import com.dev.crm.features.manager.SendMessageToClient.dto.SendMessageToClientRequest;
import com.dev.crm.features.manager.SendMessageToClient.dto.SendMessageToClientResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages/clients/send")
public class SendMessageToClientController {
    private final SendMessageToClientUseCase useCase;

    public SendMessageToClientController(SendMessageToClientUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/")
    public ApiResponse<SendMessageToClientResponse> send(@RequestBody @Valid SendMessageToClientRequest request) {
        Long currentAuthUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SendMessageToClientResponse useCaseResult = useCase.handle(request, currentAuthUserId);

        return ApiResponse.ok(useCaseResult);
    }
}
