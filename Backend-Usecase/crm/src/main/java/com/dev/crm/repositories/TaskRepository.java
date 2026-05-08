package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.entities.Task;

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

    @Modifying
    @Transactional
    @Query(value="UPDATE task SET client = :clientId, version = version + 1 WHERE id = :taskId AND version = :version", nativeQuery=true)
    public Long updateClientId(
        @Param("taskId") Long taskId, 
        @Param("clientId") Long clientId,
        @Param("version") int version
    );

    @Modifying
    @Transactional
    @Query(value="UPDATE task SET status = :statusId, version = version + 1 WHERE id = :taskId AND version = :version", nativeQuery=true)
    public Long updateStatusId(
        @Param("taskId") Long taskId, 
        @Param("statusId") Long statusId,
        @Param("version") int version
    );

    @Modifying
    @Transactional
    @Query(value="DELETE FROM task WHERE id = :taskId AND version = :version", nativeQuery=true)
    public Long deleteByIdWithVersion(
        @Param("taskId") Long taskId, 
        @Param("version") int  version
    );
}