package com.dev.crm.features.employee.GetTasksList.contracts;

import java.util.List;

import com.dev.crm.features.employee.GetTasksList.dto.GetTasksListTaskDTO;

public interface GetTasksListContract {
    /**
     * Получает список задач сотрудника
     * @param employeeId - ID сотрудника
     * @return - список задач 
     */
    public List<GetTasksListTaskDTO> getEmployeeTasks(Long employeeId);
}
