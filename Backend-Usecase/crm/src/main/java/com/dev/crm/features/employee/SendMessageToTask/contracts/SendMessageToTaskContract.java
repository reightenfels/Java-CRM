package com.dev.crm.features.employee.SendMessageToTask.contracts;

import java.time.LocalDateTime;

public interface SendMessageToTaskContract {
    /**
     * Создает сообщение
     * @param text - Содержимое
     * @param senderId - ID отправителя
     * @param dialogId - ID диалога
     * @param createdAt - Дата создания
     * @return - ID созданного сообщения
     */
    public Long createMessage(
        String text,
        Long senderId,
        Long dialogId,
        LocalDateTime createdAt
    );
}
