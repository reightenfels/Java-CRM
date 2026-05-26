package com.dev.crm.features.employee.OpenTasksDialogsList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.employee.OpenTasksDialogsList.contracts.OpenTasksDialogsListContract;
import com.dev.crm.features.employee.OpenTasksDialogsList.dto.EmployeeDialogDTO;
import com.dev.crm.features.employee.OpenTasksDialogsList.dto.OpenTasksDialogsListResponse;

@Service
public class OpenTasksDialogsListUseCase {
    private final OpenTasksDialogsListContract openTasksDialogsListService;

    public OpenTasksDialogsListUseCase(OpenTasksDialogsListContract openTasksDialogsListService) {
        this.openTasksDialogsListService = openTasksDialogsListService;
    }

    @Transactional
    public OpenTasksDialogsListResponse handle(Long userId) {
        List<EmployeeDialogDTO> dialogs = openTasksDialogsListService.getUserActiveTasksDialogs(userId);

        return new OpenTasksDialogsListResponse(
            dialogs
        );
    }
}