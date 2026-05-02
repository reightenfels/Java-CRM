package com.dev.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    
}
