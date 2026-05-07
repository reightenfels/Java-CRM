package com.dev.crm.features.employee.OpenTasksDialogsList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.TaskDialog;
import com.dev.crm.features.employee.OpenTasksDialogsList.contracts.OpenTasksDialogsListContract;
import com.dev.crm.features.employee.OpenTasksDialogsList.dto.OpenTasksDialogsListResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenTasksDialogsListUseCase {
    private final OpenTasksDialogsListContract openTasksDialogsListService;

    public OpenTasksDialogsListUseCase(OpenTasksDialogsListContract openTasksDialogsListService) {
        this.openTasksDialogsListService = openTasksDialogsListService;
    }

    @Transactional
    public OpenTasksDialogsListResponse handle(Long userId) {
        List<TaskDialog> dialogs = openTasksDialogsListService.getUserActiveTasksDialogs(userId);

        return new OpenTasksDialogsListResponse(
            dialogs
        );
    }
}