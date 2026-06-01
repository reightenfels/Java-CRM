package com.dev.crm.features.manager.OpenClientCard.contracts;

import com.dev.crm.features.manager.OpenClientCard.dto.OpenClientCardDTO;

public interface OpenClientCardContract {
    /**
     * Получает клиента по логину
     * @param clientLogin - логин клиента
     * @return - Сущность клиента
     */
    public OpenClientCardDTO getClientByLogin(String clientLogin);
}
