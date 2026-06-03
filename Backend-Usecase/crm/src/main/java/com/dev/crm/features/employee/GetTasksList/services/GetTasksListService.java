package com.dev.crm.features.employee.GetTasksList.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Task;
import com.dev.crm.features.employee.GetTasksList.contracts.GetTasksListContract;
import com.dev.crm.features.employee.GetTasksList.dto.GetTasksListTaskDTO;
import com.dev.crm.repositories.TaskRepository;

@Service
public class GetTasksListService implements GetTasksListContract {
    private final TaskRepository taskRepository;

    public GetTasksListService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<GetTasksListTaskDTO> getEmployeeTasks(Long employeeId) {
        List<Task> tasksList = taskRepository.getEmployeeTasks(employeeId);

        List<GetTasksListTaskDTO> tasksListDTO = tasksList.stream()
            .map(task -> new GetTasksListTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getPrice(),
                task.getDeadline(),
                task.getResponsible().getLogin(),
                task.getClient() != null ? task.getClient().getLogin() : null,
                task.getStatus().getId(),
                task.getTaskDialog().getId()
            ))
            .collect(Collectors.toList());

        return tasksListDTO;
    }
}
