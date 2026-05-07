package com.dev.crm.features.manager.OpenTaskCard;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.entities.Task;
import com.dev.crm.features.manager.OpenTaskCard.contracts.OpenTaskContract;
import com.dev.crm.features.manager.OpenTaskCard.dto.OpenTaskResponse;

@Service
public class OpenTaskUseCase {
    private final OpenTaskContract openTaskService;

    public OpenTaskUseCase(OpenTaskContract openTaskService) {
        this.openTaskService = openTaskService;
    }

    @Transactional
    public OpenTaskResponse handle(Long taskId, Long currentUserId) {
        Task task = openTaskService.getTask(taskId);

        openTaskService.createTaskEditor(task.getId(), currentUserId);

        return new OpenTaskResponse(
            task
        );
    }
}
