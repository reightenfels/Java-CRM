package com.dev.crm.features.manager.OpenClientCard.contracts;

import com.dev.crm.entities.User;

public interface OpenClientCardContract {
    /**
     * Получает клиента по ID
     * @param clientId - ID клиента
     * @return - Сущность клиента
     */
    public User getClientById(Long clientId);
}
