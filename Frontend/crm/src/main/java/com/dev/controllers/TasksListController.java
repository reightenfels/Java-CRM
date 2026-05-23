package com.dev.controllers;

import com.dev.dto.models.TaskModel;
import com.dev.factories.TaskCardCellFactory;
import com.dev.services.Navigation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TasksListController {
    @FXML
    private ListView<TaskModel> inWorkTasksListView;

    @FXML
    private ListView<TaskModel> notStartedTasksListView;

    @FXML
    private ListView<TaskModel> completedTasksListView;

    ObservableList<TaskModel> inWorkTasksList = FXCollections.observableArrayList();
    ObservableList<TaskModel> notStartedTasksList = FXCollections.observableArrayList();
    ObservableList<TaskModel> completedTasksList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        inWorkTasksListView.setCellFactory(new TaskCardCellFactory());
        notStartedTasksListView.setCellFactory(new TaskCardCellFactory());
        completedTasksListView.setCellFactory(new TaskCardCellFactory());

        inWorkTasksList.addAll(
            new TaskModel(
                "Разработка дизайна CRM системы",
                10000,
                "14.02.2026",
                "manager_login",
                "client_login"
            ),
            new TaskModel(
                "Разработка дизайна CRM системы",
                10000,
                "14.02.2026",
                "manager_login",
                "client_login"
            )
        );

        notStartedTasksList.addAll(
            new TaskModel(
                "Разработка дизайна CRM системы",
                10000,
                "14.02.2026",
                "manager_login",
                "client_login"
            ),
            new TaskModel(
                "Разработка дизайна CRM системы",
                10000,
                "14.02.2026",
                "manager_login",
                "client_login"
            )
        );

        completedTasksList.addAll(
            new TaskModel(
                "Разработка дизайна CRM системы",
                10000,
                "14.02.2026",
                "manager_login",
                "client_login"
            ),
            new TaskModel(
                "Разработка дизайна CRM системы",
                10000,
                "14.02.2026",
                "manager_login",
                "client_login"
            )
        );
        
        inWorkTasksListView.setItems(inWorkTasksList);
        notStartedTasksListView.setItems(notStartedTasksList);
        completedTasksListView.setItems(completedTasksList);
    }

    public void handleCreateTaskClickBtn() {
        Navigation.getInstance().loadAndShowForward("/views/pages/task-create.fxml");
    }
}
