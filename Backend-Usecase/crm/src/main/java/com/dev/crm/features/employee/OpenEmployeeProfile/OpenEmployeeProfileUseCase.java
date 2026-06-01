package com.dev.crm.features.employee.OpenEmployeeProfile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.employee.OpenEmployeeProfile.contracts.OpenEmployeeProfileContract;
import com.dev.crm.features.employee.OpenEmployeeProfile.dto.OpenEmployeeProfileDTO;
import com.dev.crm.features.employee.OpenEmployeeProfile.dto.OpenEmployeeProfileResponse;

@Service
public class OpenEmployeeProfileUseCase {
    private final OpenEmployeeProfileContract userService;

    public OpenEmployeeProfileUseCase(OpenEmployeeProfileContract userService) {
        this.userService = userService;
    }

    @Transactional
    public OpenEmployeeProfileResponse handle(String login) {
        OpenEmployeeProfileDTO employee = userService.getEmployeeByLogin(login);

        return new OpenEmployeeProfileResponse(
            employee
        );
    }
}