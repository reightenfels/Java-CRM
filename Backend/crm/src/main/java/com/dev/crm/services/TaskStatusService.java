package com.dev.crm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.contracts.services.TaskStatusServiceContract;
import com.dev.crm.entities.TaskStatus;
import com.dev.crm.repositories.TaskStatusRepository;

@Service
public class TaskStatusService implements TaskStatusServiceContract {
    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public List<TaskStatus> findAll() {
        return this.taskStatusRepository.findAll();
    }

    @Override
    public Long create(TaskStatus taskStatus) {
        Long taskStatusId = this.taskStatusRepository.save(taskStatus).getId();
        taskStatus.setId(taskStatusId);
        return taskStatusId;
    }
}
