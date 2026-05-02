package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dev.crm.entities.Task;

import jakarta.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Transactional
    @Query(value = "UDPATE task SET status = ?1 WHERE id = ?2")
    public int updateStatus(Long statusId, Long taskId);

    public List<Task> findByStatusId(Long statusId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET is_editing = 1 WHERE id = ?1")
    public int makeEditingOn(Long taskId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET is_editing = 0 WHERE id = ?1")
    public int makeEditingOff(Long taskId);
}