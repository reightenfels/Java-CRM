package com.dev.crm.contracts.services;

import com.dev.crm.entities.Role;

public interface RoleServiceContract {
    /**
     * Возвращает роль с мниимальным приоритетом
     */
    public Role findByMinLevel();
}
