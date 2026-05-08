package com.dev.crm.features.manager.CloseTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.CloseTask.contracts.CloseTaskContract;
import com.dev.crm.features.manager.CloseTask.dto.CloseTaskRequest;
import com.dev.crm.features.manager.CloseTask.dto.CloseTaskResponse;
import com.dev.crm.features.manager.CloseTask.services.CloseTaskNotificationService;

@Service
public class CloseTaskUseCase {
    private final CloseTaskContract closeTaskService;
    private final CloseTaskNotificationService notificationService;

    public CloseTaskUseCase(
        CloseTaskContract closeTaskService,
        CloseTaskNotificationService notificationService
    ) {
        this.closeTaskService = closeTaskService;
        this.notificationService = notificationService;
    }

    @Transactional
    public CloseTaskResponse handle(CloseTaskRequest request, Long currentUserId) {
        closeTaskService.removeUserFromTaskEditors(
            currentUserId,
            request.getTaskId()
        );

        notificationService.send(request.getTaskId());

        return new CloseTaskResponse(
            request.getTaskId()
        );
    }
}
