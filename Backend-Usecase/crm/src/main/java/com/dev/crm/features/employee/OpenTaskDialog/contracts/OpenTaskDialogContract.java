package com.dev.crm.features.employee.OpenTaskDialog.contracts;

import java.util.List;

import com.dev.crm.features.employee.OpenTaskDialog.dto.OpenTaskDialogMessageDTO;

public interface OpenTaskDialogContract {
    /**
     * Получает сообщения диалога
     * @param dialogId - ID диалога
     * @return - список сообщений
     */
    public List<OpenTaskDialogMessageDTO> getAllDialogMessages(Long dialogId, Long currentUserId);
}
