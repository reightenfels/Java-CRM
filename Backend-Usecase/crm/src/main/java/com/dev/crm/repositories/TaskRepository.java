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
    @Query(value = "UPDATE task SET status = ?1 WHERE id = ?2", nativeQuery=true)
    public int updateStatus(Long statusId, Long taskId);

    public List<Task> findByStatusId(Long statusId);

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

    @Query(value="""
            SELECT
                t.*
            FROM
                task t
            JOIN
                users_tasks_map m
            ON
                t.id = m.task_id
            WHERE
                m.user_id = :employeeId
            """, nativeQuery=true)
    public List<Task> getEmployeeTasks(@Param("employeeId") Long employeeId);
}