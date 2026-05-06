package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.TaskDialogMessage;

public interface TaskDialogMessagesRepository extends JpaRepository<TaskDialogMessage, Long> {
    public List<TaskDialogMessage> findByDialogId(Long dialogId);
}
