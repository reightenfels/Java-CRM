package com.dev.crm.features.manager.GetManagersList.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.GetManagersList.GetManagersListUseCase;
import com.dev.crm.features.manager.GetManagersList.dto.GetManagersListResponse;

@RestController
@RequestMapping("/api/managers")
public class GetManagersListController {
    private final GetManagersListUseCase useCase;

    public GetManagersListController(GetManagersListUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/logins")
    public ApiResponse<GetManagersListResponse> getLogins() {
        GetManagersListResponse useCaseResult = useCase.handle();

        return ApiResponse.ok(useCaseResult);
    }
}
