package com.dev.crm.features.manager.CreateClient.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.CreateClient.CreateClientUseCase;
import com.dev.crm.features.manager.CreateClient.dto.CreateClientRequest;
import com.dev.crm.features.manager.CreateClient.dto.CreateClientResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients/create")
public class CreateClientController {
    private final CreateClientUseCase useCase;

    public CreateClientController(CreateClientUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/")
    public ApiResponse<CreateClientResponse> create(@RequestBody @Valid CreateClientRequest request) {
        CreateClientResponse useCaseResult = useCase.handle(request);

        return ApiResponse.ok(useCaseResult);
    }
}
