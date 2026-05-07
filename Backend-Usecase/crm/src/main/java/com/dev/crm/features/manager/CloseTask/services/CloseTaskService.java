package com.dev.crm.features.manager.CloseTask.services;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.CloseTask.contracts.CloseTaskContract;
import com.dev.crm.repositories.TaskEditorsRepository;

@Service
public class CloseTaskService implements CloseTaskContract {
    private final TaskEditorsRepository editorsRepository;

    public CloseTaskService(TaskEditorsRepository editorsRepository) {
        this.editorsRepository = editorsRepository;
    }

    @Override
    public Long removeUserFromTaskEditors(Long userId, Long taskId) {
        return editorsRepository.deleteByTaskIdAndUserId(taskId, userId);
    }
}
