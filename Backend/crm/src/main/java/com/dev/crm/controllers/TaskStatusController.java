package com.dev.crm.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.entities.TaskStatus;
import com.dev.crm.services.taskstatus.ITaskStatusService;

@RestController
@RequestMapping("/api/task-statuses")
public class TaskStatusController {
    private final ITaskStatusService taskStatusService;

    public TaskStatusController(ITaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @GetMapping("/")
    public List<TaskStatus> findAll() {
        return this.taskStatusService.findAll();
    }

    @PostMapping("/create")
    public Long create(@RequestBody TaskStatus taskStatus) {
        return this.taskStatusService.create(taskStatus);
    }
}
