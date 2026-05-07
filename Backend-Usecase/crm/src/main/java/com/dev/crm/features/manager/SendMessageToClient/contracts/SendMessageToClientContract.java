package com.dev.crm.features.manager.SendMessageToClient.contracts;

import java.time.LocalDateTime;

public interface SendMessageToClientContract {
    /**
     * Создает сообщение
     * @param text - Текст
     * @param senderId - ID отправителя
     * @param dialogId - ID диалога
     * @param createdAt - Время отправки
     * @return - ID созданного сообщения
     */
    public Long createMessage(
        String text,
        Long senderId,
        Long dialogId,
        LocalDateTime createdAt
    );
}
