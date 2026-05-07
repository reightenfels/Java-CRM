package com.dev.crm.features.manager.OpenClientDialog.contracts;

import java.util.List;

import com.dev.crm.entities.ClientDialogMessage;

public interface OpenClientDialogContract {
    /**
     * Получает все сообщения диалога
     * @param Long dialogId
     * @return - список сообщений
     */
    public List<ClientDialogMessage> getAllDialogMessages(Long dialogId);
}
