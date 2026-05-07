package com.dev.crm.features.manager.CloseTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.CloseTask.contracts.CloseTaskContract;
import com.dev.crm.features.manager.CloseTask.dto.CloseTaskRequest;
import com.dev.crm.features.manager.CloseTask.dto.CloseTaskResponse;

@Service
public class CloseTaskUseCase {
    private final CloseTaskContract closeTaskService;

    public CloseTaskUseCase(CloseTaskContract closeTaskService) {
        this.closeTaskService = closeTaskService;
    }

    @Transactional
    public CloseTaskResponse handle(CloseTaskRequest request, Long currentUserId) {
        closeTaskService.removeUserFromTaskEditors(
            currentUserId,
            request.getTaskId()
        );

        // TODO: Добавить задачу в очередь

        return new CloseTaskResponse(
            request.getTaskId()
        );
    }
}
