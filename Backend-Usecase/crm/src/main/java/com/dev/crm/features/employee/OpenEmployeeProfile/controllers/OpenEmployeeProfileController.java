package com.dev.crm.features.employee.OpenEmployeeProfile.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.features.employee.OpenEmployeeProfile.OpenEmployeeProfileUseCase;
import com.dev.crm.features.employee.OpenEmployeeProfile.dto.OpenEmployeeProfileResponse;

@RestController
@RequestMapping("/api/users")
public class OpenEmployeeProfileController {
    private final OpenEmployeeProfileUseCase useCase;

    public OpenEmployeeProfileController(OpenEmployeeProfileUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ApiResponse<OpenEmployeeProfileResponse> getByLogin(@RequestParam("login") String login) {
        OpenEmployeeProfileResponse useCaseResult = useCase.handle(login);
        
        return ApiResponse.ok(useCaseResult);
    }
}
