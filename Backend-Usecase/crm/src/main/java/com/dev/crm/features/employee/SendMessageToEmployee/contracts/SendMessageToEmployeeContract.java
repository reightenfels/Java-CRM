package com.dev.crm.features.employee.SendMessageToEmployee.contracts;

import java.time.LocalDateTime;

public interface SendMessageToEmployeeContract {
    /**
     * Создает диалог
     * @param user1 - ID одного собеседника
     * @param user2 - ID второго собеседника
     * @param avatarId: ID вложения - обложки
     * @return - ID созданного диалога
     */
    public Long createDialog(
        Long user1,
        Long user2,
        Long avatarId        
    );

    /**
     * Проверяет наличие диалога по ID
     * @param dialogId - ID диалога
     * @return - Есть\нет
     */
    public Boolean isDialogExists(Long dialogId);

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
