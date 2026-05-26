package com.dev.crm.features.employee.OpenTasksDialogsList.contracts;

import java.util.List;

import com.dev.crm.features.employee.OpenTasksDialogsList.dto.EmployeeDialogDTO;

public interface OpenTasksDialogsListContract {
    /**
     * Получает диалоги по активным задачам сотрудника
     * @param userId - ID сотрудника
     * @return - список диалогов
     */
    public List<EmployeeDialogDTO> getUserActiveTasksDialogs(Long userId);
}
