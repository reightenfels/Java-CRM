package com.dev.crm.features.manager.GetAllClientsLogins.contracts;

import java.util.Set;

public interface GetAllClientsLoginsContract {
    /**
     * Возвращет список логинов всех клиентов
     */
    public Set<String> getAllClientsLogins();
}
