package com.dev.crm.features.manager.OpenTaskCard;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.OpenTaskCard.contracts.OpenTaskContract;
import com.dev.crm.features.manager.OpenTaskCard.dto.OpenTaskCardDTO;
import com.dev.crm.features.manager.OpenTaskCard.dto.OpenTaskResponse;
import com.dev.crm.features.manager.OpenTaskCard.services.OpenTaskCardNotificationService;

@Service
public class OpenTaskUseCase {
    private final OpenTaskContract openTaskService;
    private final OpenTaskCardNotificationService notificationService;

    public OpenTaskUseCase(
        OpenTaskContract openTaskService,
        OpenTaskCardNotificationService notificationService
    ) {
        this.openTaskService = openTaskService;
        this.notificationService = notificationService;
    }

    public OpenTaskResponse handle(Long taskId, Long currentUserId) {
        OpenTaskCardDTO task = openTaskService.getTask(taskId);

        openTaskService.createTaskEditor(taskId, currentUserId);

        notificationService.send(taskId);

        return new OpenTaskResponse(
            task
        );
    }
}
