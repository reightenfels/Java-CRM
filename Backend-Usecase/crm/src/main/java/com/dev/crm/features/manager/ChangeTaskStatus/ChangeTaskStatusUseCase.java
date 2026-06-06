package com.dev.crm.features.manager.ChangeTaskStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.ChangeTaskStatus.contracts.ChangeTaskStatusContract;
import com.dev.crm.features.manager.ChangeTaskStatus.dto.ChangeTaskStatusRequest;
import com.dev.crm.features.manager.ChangeTaskStatus.dto.ChangeTaskStatusResponse;
import com.dev.crm.features.manager.ChangeTaskStatus.services.ChangeTaskStatusNotificationService;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;

@Service
public class ChangeTaskStatusUseCase {
    private final ChangeTaskStatusContract changeTaskStatusService;
    private final ChangeTaskStatusNotificationService notificationService;

    public ChangeTaskStatusUseCase(
        ChangeTaskStatusContract changeTaskStatusService,
        ChangeTaskStatusNotificationService notificationService
    ) {
        this.changeTaskStatusService = changeTaskStatusService;
        this.notificationService = notificationService;
    }

    @Transactional
    public ChangeTaskStatusResponse handle(
        Long taskId,
        ChangeTaskStatusRequest request
    ) {
        Long countUpdates = changeTaskStatusService.updateTaskStatus(
            taskId,
            request.getStatusSystemName(),
            request.getVersion()
        );

        if (countUpdates == 0) {
            throw new OldEntityVersionException("Есть более новая версия данной задачи");
        }

        notificationService.send(taskId, request.getStatusSystemName());

        return new ChangeTaskStatusResponse(
            taskId
        );
    }
}
