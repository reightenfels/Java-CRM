package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crm.entities.TaskDialog;

public interface TaskDialogRepository extends JpaRepository<TaskDialog, Long> {
    @Query(value = """
            SELECT
                d.*
            FROM task_dialog d
            JOIN 
                users_tasks_map u ON d.task_id = u.task_id
            WHERE
                u.user_id = ?1
            """, nativeQuery = true)
    public List<TaskDialog> findAllUseActiveDialogs(Long userId);
}
