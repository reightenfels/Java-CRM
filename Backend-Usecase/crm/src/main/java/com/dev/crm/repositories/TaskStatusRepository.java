package com.dev.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    public Optional<TaskStatus> findByDisplayName(String displayName);
    public Optional<TaskStatus> findBySystemName(String systemName);
}
