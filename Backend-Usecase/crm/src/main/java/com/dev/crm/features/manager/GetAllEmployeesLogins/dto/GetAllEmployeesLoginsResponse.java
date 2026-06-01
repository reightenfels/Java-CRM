package com.dev.crm.features.manager.GetAllEmployeesLogins.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAllEmployeesLoginsResponse {
    private final List<String> employees;
}
