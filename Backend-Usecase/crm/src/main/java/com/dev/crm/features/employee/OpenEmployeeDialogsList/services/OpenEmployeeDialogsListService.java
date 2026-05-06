package com.dev.crm.features.employee.OpenEmployeeDialogsList.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.PersonalEmployeeDialog;
import com.dev.crm.features.employee.OpenEmployeeDialogsList.contracts.OpenEmployeeDialogsListContract;
import com.dev.crm.repositories.EmployeeDialogRepository;

@Service
public class OpenEmployeeDialogsListService implements OpenEmployeeDialogsListContract {
    private final EmployeeDialogRepository dialogRepository;

    public OpenEmployeeDialogsListService(EmployeeDialogRepository dialogRepository) {
        this.dialogRepository = dialogRepository;
    }

    @Override
    public List<PersonalEmployeeDialog> getUserEmployeeDialogs(Long userId) {
        return dialogRepository.findAllByUser(userId);
    } 
}
