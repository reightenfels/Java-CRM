package com.dev.crm.services.task;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Task;
import com.dev.crm.repositories.TaskRepository;

@Service
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return this.taskRepository.findById(taskId);
    }

    @Override
    public Boolean updateStatus(Long statusId, Long taskId) {
        int updatedCount = this.taskRepository.updateStatus(statusId, taskId);
        return updatedCount > 0;
    }

    @Override
    public List<Task> findByStatusId(Long statusId) {
        return this.taskRepository.findByStatusId(statusId);
    }

    @Override
    public Long update(Task task) {
        return this.taskRepository.save(task).getId();
    }

    @Override
    public Long delete(Long taskId) {
        this.taskRepository.deleteById(taskId);
        return taskId;
    }

    @Override
    public Long create(Task task) {
        Long taskId = this.taskRepository.save(task).getId();
        task.setId(taskId);
        return taskId;
    }

    @Override
    public Boolean makeEditingOn(Long taskId) {
        int updatedCount = this.taskRepository.makeEditingOn(taskId);
        return updatedCount > 0;
    }

    @Override
    public Boolean makeEditingOff(Long taskId) {
        int updatedCount = this.taskRepository.makeEditingOff(taskId);
        return updatedCount > 0;
    }
}
