package com.dev.crm.features.manager.DeleteClient.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.DeleteClient.DeleteClientUseCase;
import com.dev.crm.features.manager.DeleteClient.dto.DeleteClientRequest;
import com.dev.crm.features.manager.DeleteClient.dto.DeleteClientResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients/delete")
public class DeleteClientController {
    private final DeleteClientUseCase useCase;

    public DeleteClientController(DeleteClientUseCase useCase) {
        this.useCase = useCase;
    }

    @DeleteMapping("/")
    public ApiResponse<DeleteClientResponse> delete(@RequestBody @Valid DeleteClientRequest request) {
        DeleteClientResponse useCaseResult = useCase.handle(request);

        return ApiResponse.ok(useCaseResult);
    }
}
