package com.dev.crm.features.employee.DeleteEmployeeDialog;

import org.springframework.stereotype.Service;

import com.dev.crm.features.employee.DeleteEmployeeDialog.contracts.DeleteEmployeeContract;
import com.dev.crm.features.employee.DeleteEmployeeDialog.dto.DeleteEmployeeDialogRequest;
import com.dev.crm.features.employee.DeleteEmployeeDialog.dto.DeleteEmployeeDialogResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteEmployeeDialogUseCase {
    private final DeleteEmployeeContract deleteEmployeeService;

    public DeleteEmployeeDialogUseCase(
        DeleteEmployeeContract deleteEmployeeService
    ) {
        this.deleteEmployeeService = deleteEmployeeService;
    }

    @Transactional
    public DeleteEmployeeDialogResponse handle(DeleteEmployeeDialogRequest request) {
        deleteEmployeeService.deleteAllDialogMessages(request.getDialogId());
        Long deletedDialogId = deleteEmployeeService.deleteDialog(request.getDialogId());

        // TODO: Добавить событие в очередь

        return new DeleteEmployeeDialogResponse(
            deletedDialogId
        );
    }
}
