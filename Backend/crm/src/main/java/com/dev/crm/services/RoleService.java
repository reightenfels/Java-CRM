package com.dev.crm.services;

import com.dev.crm.contracts.services.RoleServiceContract;
import com.dev.crm.entities.Role;
import com.dev.crm.repositories.RoleRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

public class RoleService implements RoleServiceContract {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role findByMinLevel() {
        return repository.findByMinLevel().orElseThrow(
            () -> new ResourceNotFoundException("Не найдена роль с минимальным приоритетом")
        );
    }
}
