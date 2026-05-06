package com.dev.crm.features.employee.OpenTasksDialogsList.contracts;

import java.util.List;

import com.dev.crm.entities.TaskDialog;

public interface OpenTasksDialogsListContract {
    /**
     * Получает диалоги по активным задачам сотрудника
     * @param userId - ID сотрудника
     * @return - список диалогов
     */
    public List<TaskDialog> getUserActiveTasksDialogs(Long userId);
}
