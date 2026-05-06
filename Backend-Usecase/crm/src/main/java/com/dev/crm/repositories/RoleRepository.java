package com.dev.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crm.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value="""
            SELECT
                *
            FROM role
            ORDERBY
                level ASC,
                id ASC
            LIMIT 1
            """, nativeQuery=true)
    public Optional<Role> findByMinLevel();
}
