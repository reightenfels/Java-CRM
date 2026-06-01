package com.dev.crm.features.manager.OpenClientCard.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.OpenClientCard.OpenClientCardUseCase;
import com.dev.crm.features.manager.OpenClientCard.dto.OpenClientCardResponse;

@RestController
@RequestMapping("/api/clients")
public class OpenClientCardController {
    private final OpenClientCardUseCase useCase;

    public OpenClientCardController(OpenClientCardUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ApiResponse<OpenClientCardResponse> getByLogin(@RequestParam("login") String login) {
        OpenClientCardResponse useCaseResult = useCase.handle(login);

        return ApiResponse.ok(useCaseResult);
    }
}
