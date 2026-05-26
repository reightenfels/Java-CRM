package com.dev.crm.features.manager.GetManagersList.contracts;

import java.util.List;

public interface GetManagersListContract {
    /**
     * Возвращает список логинов всех менеджеров
     * @return - Список логинов
     */
    public List<String> getAllManagersLogins();
}
