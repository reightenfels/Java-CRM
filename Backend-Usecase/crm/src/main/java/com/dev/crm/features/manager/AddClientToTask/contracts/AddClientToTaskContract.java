package com.dev.crm.features.manager.AddClientToTask.contracts;

public interface AddClientToTaskContract {
    /**
     * Прикрепляет клиента к задаче
     * @param taskId - ID задачи
     * @param clientId - ID клиента
     * @param version - Версия задачи
     * @return - Количество измененных строк
     */
    public Long addClientToTask(
        Long taskId, 
        Long clientId,
        int version
    );
}
