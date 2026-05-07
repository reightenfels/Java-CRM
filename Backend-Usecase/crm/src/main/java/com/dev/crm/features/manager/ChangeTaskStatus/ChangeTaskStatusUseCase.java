package com.dev.crm.features.manager.ChangeTaskStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.ChangeTaskStatus.contracts.ChangeTaskStatusContract;
import com.dev.crm.features.manager.ChangeTaskStatus.dto.ChangeTaskStatusRequest;
import com.dev.crm.features.manager.ChangeTaskStatus.dto.ChangeTaskStatusResponse;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;

@Service
public class ChangeTaskStatusUseCase {
    private final ChangeTaskStatusContract changeTaskStatusService;

    public ChangeTaskStatusUseCase(ChangeTaskStatusContract changeTaskStatusService) {
        this.changeTaskStatusService = changeTaskStatusService;
    }

    @Transactional
    public ChangeTaskStatusResponse handle(ChangeTaskStatusRequest request) {
        Long countUpdates = changeTaskStatusService.updateTaskStatus(
            request.getTaksId(),
            request.getStatusId(),
            request.getVersion()
        );

        if (countUpdates == 0) {
            throw new OldEntityVersionException("Есть более новая версия данной задачи");
        }

        // TODO: Отправить задачу в очередь

        return new ChangeTaskStatusResponse(
            request.getTaksId()
        );
    }
}
