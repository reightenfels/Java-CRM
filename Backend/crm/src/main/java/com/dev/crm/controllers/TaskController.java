package com.dev.crm.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.entities.Task;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.services.task.ITaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return this.taskService.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Не найдена задача с ID: " + id);
        });
    }

    @PostMapping("/update-status")
    public Boolean updateStatus(@RequestParam Long taskId, @RequestParam Long statusId) {
        return this.taskService.updateStatus(statusId, taskId);
    }

    @PostMapping("/get-by-status")
    public List<Task> findByStatusId(@RequestParam Long statusId) {
        return this.taskService.findByStatusId(statusId);
    }

    @PostMapping("/update")
    public Long update(@RequestBody Task task) {
        return this.taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public Long delete(@RequestParam Long taskId) {
        return this.taskService.delete(taskId);
    }

    @PostMapping("/create")
    public Long create(@RequestBody Task task) {
        return this.taskService.create(task);
    }

    @PostMapping("/make-editing-on")
    public Boolean makeEditingOn(@RequestParam Long taskId) {
        return this.taskService.makeEditingOn(taskId);
    }

    @PostMapping("/make-editing-off")
    public Boolean makeEditingOff(@RequestParam Long taskId) {
        return this.taskService.makeEditingOff(taskId);
    }
}
