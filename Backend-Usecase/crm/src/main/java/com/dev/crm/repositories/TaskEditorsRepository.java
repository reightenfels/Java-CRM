package com.dev.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.TaskEditors;

public interface TaskEditorsRepository extends JpaRepository<TaskEditors, Long> {
    public Long deleteByTaskIdAndUserId(Long taskId, Long userId);
}
