package com.dev.crm.features.manager.GetAllEmployeesLogins;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.GetAllEmployeesLogins.contracts.GetAllEmployeesLoginsContract;
import com.dev.crm.features.manager.GetAllEmployeesLogins.dto.GetAllEmployeesLoginsResponse;

@Service
public class GetAllEmployeesUseCase {
    private final GetAllEmployeesLoginsContract employeesService;

    public GetAllEmployeesUseCase(GetAllEmployeesLoginsContract employeesService) {
        this.employeesService = employeesService;
    }

    @Transactional
    public GetAllEmployeesLoginsResponse handle() {
        List<String> employees = employeesService.getAllEmployeesLogins();

        return new GetAllEmployeesLoginsResponse(
            employees
        );
    }
}
