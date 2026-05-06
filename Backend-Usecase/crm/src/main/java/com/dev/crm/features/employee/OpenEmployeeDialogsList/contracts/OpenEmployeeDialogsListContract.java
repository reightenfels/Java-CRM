package com.dev.crm.features.employee.OpenEmployeeDialogsList.contracts;

import java.util.List;

import com.dev.crm.entities.PersonalEmployeeDialog;

public interface OpenEmployeeDialogsListContract {
    /**
     * Возвращает все диалоги пользователя с сотрудниками
     * @param userId - ID пользователя
     * @return - список диалогов
     */
    public List<PersonalEmployeeDialog> getUserEmployeeDialogs(Long userId);
}
