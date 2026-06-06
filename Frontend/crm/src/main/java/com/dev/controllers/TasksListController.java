package com.dev.controllers;

import java.util.HashMap;
import java.util.Map;

import com.dev.controllers.parts.TaskColumnController;
import com.dev.dto.models.TaskCardModel;
import com.dev.features.employee.GetTasksList.GetTasksListUseCase;
import com.dev.services.Navigation;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class TasksListController {
    @FXML
    public HBox statusesColumnsHBox;

    public Map<Long, TaskColumnController> columnsMap = new HashMap<>();

    public Map<Long, TaskCardModel> tasksCardsMap = new HashMap<>();

    @FXML
    public void initialize() {
        GetTasksListUseCase.execute(this);
    }

    /**
     * Переход на страницу создания задачи
     */
    public void handleCreateTaskClickBtn() {
        Navigation.getInstance().loadAndShowForwardTyped(
            "/views/pages/task-create.fxml",
            TaskCreateController.class,
            controller -> controller.loadData()
        );
    }
}
