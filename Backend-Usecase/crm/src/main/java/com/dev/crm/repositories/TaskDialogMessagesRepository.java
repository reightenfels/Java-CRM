package com.dev.crm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.entities.TaskDialogMessage;

public interface TaskDialogMessagesRepository extends JpaRepository<TaskDialogMessage, Long> {
    public List<TaskDialogMessage> findByDialogId(Long dialogId);

    @Modifying
    @Transactional
    @Query(value = """
            DELETE FROM task_dialog_message
            WHERE 
                dialog_id = (SELECT id FROM task_dialog WHERE task_id = :taskId)
            """, nativeQuery=true)
    public Long deleteByTaskId(@Param("taskId") Long taskId);

    Optional<TaskDialogMessage> findTopByDialogIdOrderByIdDesc(Long dialogId);
}
