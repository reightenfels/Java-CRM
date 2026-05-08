package com.dev.crm.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crm.entities.TaskEditors;

public interface TaskEditorsRepository extends JpaRepository<TaskEditors, Long> {
    public Long deleteByTaskIdAndUserId(Long taskId, Long userId);
    public List<TaskEditors> findByTaskId(Long taskId);

    @Query(value = """
            SELECT
                u.login
            FROM 
                user u
            JOIN 
                task_editor e ON e.user_id = u.id
            JOIN 
                task t ON e.task_id = t.id
            WHERE
                t.id = ?1
            """, nativeQuery=true)
    public Set<String> findTaskEditorsLogins(Long taskId);
}
