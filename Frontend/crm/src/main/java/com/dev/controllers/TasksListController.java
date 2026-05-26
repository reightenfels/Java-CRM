package com.dev.controllers;

import java.util.HashMap;
import java.util.Map;

import com.dev.controllers.parts.TaskColumnController;
import com.dev.features.employee.GetTasksList.GetTasksListUseCase;
import com.dev.services.Navigation;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class TasksListController {
    @FXML
    private HBox statusesColumnsHBox;

    Map<Long, TaskColumnController> columnsMap = new HashMap<>();

    @FXML
    public void initialize() {
        GetTasksListUseCase.execute(columnsMap, statusesColumnsHBox);
    }

    public void handleCreateTaskClickBtn() {
        Navigation.getInstance().loadAndShowForward("/views/pages/task-create.fxml");
    }
}
