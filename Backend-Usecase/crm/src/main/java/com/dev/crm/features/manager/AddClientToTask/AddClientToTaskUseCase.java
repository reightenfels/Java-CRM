package com.dev.crm.features.manager.AddClientToTask;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.AddClientToTask.contracts.AddClientToTaskContract;
import com.dev.crm.features.manager.AddClientToTask.dto.AddClientToTaskRequest;
import com.dev.crm.features.manager.AddClientToTask.dto.AddClientToTaskResponse;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AddClientToTaskUseCase {
    private final AddClientToTaskContract addClientToTaskService;

    public AddClientToTaskUseCase(AddClientToTaskContract addClientToTaskService) {
        this.addClientToTaskService = addClientToTaskService;
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

        // TODO: Добавить событие в очередь

        return new AddClientToTaskResponse(
            request.getTaskId()
        );
    }
}
