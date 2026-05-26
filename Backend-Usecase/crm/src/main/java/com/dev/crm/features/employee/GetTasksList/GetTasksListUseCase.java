package com.dev.crm.features.employee.GetTasksList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.employee.GetTasksList.contracts.GetTasksListContract;
import com.dev.crm.features.employee.GetTasksList.dto.GetTasksListResponse;
import com.dev.crm.features.employee.GetTasksList.dto.GetTasksListTaskDTO;

@Service
public class GetTasksListUseCase {
    private final GetTasksListContract taskListsService;

    public GetTasksListUseCase(GetTasksListContract taskListsService) {
        this.taskListsService = taskListsService;
    }

    @Transactional
    public GetTasksListResponse handle(Long userId) {
        List<GetTasksListTaskDTO> tasksList = taskListsService.getEmployeeTasks(userId);

        return new GetTasksListResponse(
            tasksList
        );
    }
}
