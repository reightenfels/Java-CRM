package com.dev.crm.features.employee.OpenEmployeeDialog.contracts;

import java.util.List;

import com.dev.crm.entities.PersonalEmployeeDialogMessage;

public interface OpenEmployeeDialogContract {
    /**
     * Получает все сообщения диалога
     * @param dialogId - ID диалога 
     * @return - список сообщений
     */
    public List<PersonalEmployeeDialogMessage> getAllDialogMessages(Long dialogId);
}
