package com.dev.crm.features.manager.AddClientToTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.AddClientToTask.contracts.AddClientToTaskContract;
import com.dev.crm.features.manager.AddClientToTask.dto.AddClientToTaskRequest;
import com.dev.crm.features.manager.AddClientToTask.dto.AddClientToTaskResponse;
import com.dev.crm.features.manager.AddClientToTask.services.AddClientToTaskNotificationService;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;

@Service
public class AddClientToTaskUseCase {
    private final AddClientToTaskContract addClientToTaskService;
    private final AddClientToTaskNotificationService notificationService;

    public AddClientToTaskUseCase(
        AddClientToTaskContract addClientToTaskService,
        AddClientToTaskNotificationService notificationService
    ) {
        this.addClientToTaskService = addClientToTaskService;
        this.notificationService = notificationService;
    }

    @Transactional
    public AddClientToTaskResponse handle(AddClientToTaskRequest request) {
        Long countUpdates = addClientToTaskService.addClientToTask(
            request.getTaskId(),
            request.getClientId(),
            request.getVersion()
        );

        if (countUpdates == 0) {
            throw new OldEntityVersionException("Есть более поздняя версия этой задачи");
        }

        notificationService.send(
            request.getTaskId(),
            request.getClientId()
        );

        return new AddClientToTaskResponse(
            request.getTaskId()
        );
    }
}
