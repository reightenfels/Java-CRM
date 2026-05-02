package com.dev.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
