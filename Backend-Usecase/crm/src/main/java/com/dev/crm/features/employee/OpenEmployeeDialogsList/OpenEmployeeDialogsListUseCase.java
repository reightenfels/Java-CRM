package com.dev.crm.features.employee.OpenEmployeeDialogsList;

import java.util.List;

import com.dev.crm.entities.PersonalEmployeeDialog;
import com.dev.crm.features.employee.OpenEmployeeDialogsList.contracts.OpenEmployeeDialogsListContract;
import com.dev.crm.features.employee.OpenEmployeeDialogsList.dto.OpenEmployeeDialogsListResponse;

import jakarta.transaction.Transactional;

public class OpenEmployeeDialogsListUseCase {
    private final OpenEmployeeDialogsListContract employeeDialogsService;

    public OpenEmployeeDialogsListUseCase(OpenEmployeeDialogsListContract employeeDialogsService) {
        this.employeeDialogsService = employeeDialogsService;
    }

    @Transactional
    public OpenEmployeeDialogsListResponse handle(Long userId) {
        List<PersonalEmployeeDialog> dialogs = employeeDialogsService.getUserEmployeeDialogs(userId);

        return new OpenEmployeeDialogsListResponse(
            dialogs
        );
    }
}
