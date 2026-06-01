package com.dev.crm.features.manager.GetAllClientsLogins.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.GetAllClientsLogins.GetAllClientsLoginsUseCase;
import com.dev.crm.features.manager.GetAllClientsLogins.dto.GetAllClientsLoginsResponse;

@RestController
@RequestMapping("/api/client/logins")
public class GetAllClientsLoginsController {
    private final GetAllClientsLoginsUseCase useCase;

    public GetAllClientsLoginsController(GetAllClientsLoginsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ApiResponse<GetAllClientsLoginsResponse> getAll() {
        GetAllClientsLoginsResponse useCaseResult = useCase.handle();

        return ApiResponse.ok(useCaseResult);
    }
}
