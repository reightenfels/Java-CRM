package com.dev.crm.features.employee.DeleteEmployeeDialog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.employee.DeleteEmployeeDialog.contracts.DeleteEmployeeContract;
import com.dev.crm.features.employee.DeleteEmployeeDialog.dto.DeleteEmployeeDialogRequest;
import com.dev.crm.features.employee.DeleteEmployeeDialog.dto.DeleteEmployeeDialogResponse;
import com.dev.crm.queues.dto.notifications.DeleteEmployeeDialog;
import com.dev.crm.queues.producers.NotificationProducer;

@Service
public class DeleteEmployeeDialogUseCase {
    private final DeleteEmployeeContract deleteEmployeeService;
    private final NotificationProducer notificationProducer;

    public DeleteEmployeeDialogUseCase(
        DeleteEmployeeContract deleteEmployeeService,
        NotificationProducer notificationProducer
    ) {
        this.deleteEmployeeService = deleteEmployeeService;
        this.notificationProducer = notificationProducer;
    }

    @Transactional
    public DeleteEmployeeDialogResponse handle(DeleteEmployeeDialogRequest request) {
        deleteEmployeeService.deleteAllDialogMessages(request.getDialogId());
        Long deletedDialogId = deleteEmployeeService.deleteDialog(request.getDialogId());

        notificationProducer.send(
            DeleteEmployeeDialog.builder()
            .type("DeleteEmployeeDialog")
            .timestamp(System.currentTimeMillis())
            .dialogId(deletedDialogId)
            .build()
        );

        return new DeleteEmployeeDialogResponse(
            deletedDialogId
        );
    }
}
