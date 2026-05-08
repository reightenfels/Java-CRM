package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.TaskEditors;

public interface TaskEditorsRepository extends JpaRepository<TaskEditors, Long> {
    public Long deleteByTaskIdAndUserId(Long taskId, Long userId);
    public List<TaskEditors> findByTaskId(Long taskId);
}
