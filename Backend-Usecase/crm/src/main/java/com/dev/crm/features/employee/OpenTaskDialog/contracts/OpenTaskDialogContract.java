package com.dev.crm.features.employee.OpenTaskDialog.contracts;

import java.util.List;

import com.dev.crm.entities.TaskDialogMessage;

public interface OpenTaskDialogContract {
    /**
     * Получает сообщения диалога
     * @param dialogId - ID диалога
     * @return - список сообщений
     */
    public List<TaskDialogMessage> getAllDialogMessages(Long dialogId);
}
