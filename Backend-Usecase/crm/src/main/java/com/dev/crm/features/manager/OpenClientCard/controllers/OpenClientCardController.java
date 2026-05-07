package com.dev.crm.features.manager.OpenClientCard.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.OpenClientCard.OpenClientCardUseCase;
import com.dev.crm.features.manager.OpenClientCard.dto.OpenClientCardResponse;

@RestController
@RequestMapping("/api/clients/{id}")
public class OpenClientCardController {
    private final OpenClientCardUseCase useCase;

    public OpenClientCardController(OpenClientCardUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/")
    public ApiResponse<OpenClientCardResponse> get(@PathVariable("id") Long clientId) {
        OpenClientCardResponse useCaseResult = useCase.handle(clientId);

        return ApiResponse.ok(useCaseResult);
    }
}
