package com.dev.crm.features.employee.GetAllStatuses.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.employee.GetAllStatuses.GetAllStatusesUseCase;
import com.dev.crm.features.employee.GetAllStatuses.dto.GetAllStatusesResponse;

@RestController
@RequestMapping("/api/status/all")
public class GetAllStatusesController {
    private final GetAllStatusesUseCase useCase;

    public GetAllStatusesController(GetAllStatusesUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ApiResponse<GetAllStatusesResponse> getAll() {
        GetAllStatusesResponse useCaseResult = useCase.handle();

        return ApiResponse.ok(useCaseResult);
    }
}
