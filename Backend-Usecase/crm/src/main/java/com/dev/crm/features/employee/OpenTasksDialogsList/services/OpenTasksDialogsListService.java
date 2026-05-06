package com.dev.crm.features.employee.OpenTasksDialogsList.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.TaskDialog;
import com.dev.crm.features.employee.OpenTasksDialogsList.contracts.OpenTasksDialogsListContract;
import com.dev.crm.repositories.TaskDialogRepository;

@Service
public class OpenTasksDialogsListService implements OpenTasksDialogsListContract {
    private final TaskDialogRepository dialogRepository;

    public OpenTasksDialogsListService(TaskDialogRepository dialogRepository) {
        this.dialogRepository = dialogRepository;
    }

    @Override
    public List<TaskDialog> getUserActiveTasksDialogs(Long userId) {
        return dialogRepository.findAllUseActiveDialogs(userId);
    }
}
