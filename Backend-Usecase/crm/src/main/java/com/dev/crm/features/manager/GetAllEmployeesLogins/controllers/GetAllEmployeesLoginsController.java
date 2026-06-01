package com.dev.crm.features.manager.GetAllEmployeesLogins.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.manager.GetAllEmployeesLogins.GetAllEmployeesUseCase;
import com.dev.crm.features.manager.GetAllEmployeesLogins.dto.GetAllEmployeesLoginsResponse;

@RestController
@RequestMapping("/api/employees/logins")
public class GetAllEmployeesLoginsController {
    private final GetAllEmployeesUseCase useCase;

    public GetAllEmployeesLoginsController(GetAllEmployeesUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ApiResponse<GetAllEmployeesLoginsResponse> getAll() {
        GetAllEmployeesLoginsResponse useCaseResult = useCase.handle();

        return ApiResponse.ok(useCaseResult);
    }
}
