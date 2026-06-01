package com.dev.crm.features.manager.GetAllEmployeesLogins.contracts;

import java.util.List;

public interface GetAllEmployeesLoginsContract {
    /**
     * Возвращает список логинов всех сотрудников
     */
    public List<String> getAllEmployeesLogins();
}
