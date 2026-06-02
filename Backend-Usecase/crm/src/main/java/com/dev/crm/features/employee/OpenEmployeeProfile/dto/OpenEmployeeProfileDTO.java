package com.dev.crm.features.employee.OpenEmployeeProfile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OpenEmployeeProfileDTO {
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
}
