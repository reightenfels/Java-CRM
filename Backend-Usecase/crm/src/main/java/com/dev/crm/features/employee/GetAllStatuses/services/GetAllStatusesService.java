package com.dev.crm.features.employee.GetAllStatuses.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.TaskStatus;
import com.dev.crm.features.employee.GetAllStatuses.contracts.GetAllStatusesContract;
import com.dev.crm.repositories.TaskStatusRepository;

@Service
public class GetAllStatusesService implements GetAllStatusesContract {
    private final TaskStatusRepository taskStatusRepository;

    public GetAllStatusesService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public List<TaskStatus> getAllStatuses() {
        return taskStatusRepository.findAll();
    }
}
