package com.dev.crm.features.employee.GetAllStatuses.contracts;

import java.util.List;

import com.dev.crm.entities.TaskStatus;

public interface GetAllStatusesContract {
    /**
     * Возвращает все статусы
     * @return - список статусов
     */
    public List<TaskStatus> getAllStatuses();
}
