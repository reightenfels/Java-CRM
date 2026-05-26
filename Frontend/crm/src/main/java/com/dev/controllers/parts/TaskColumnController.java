package com.dev.controllers.parts;

import com.dev.dto.models.TaskModel;
import com.dev.factories.TaskCardCellFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TaskColumnController {
    @FXML
    private Label columnTitle;

    @FXML
    private ListView<TaskModel> tasksListView;

    private final ObservableList<TaskModel> tasksObsList = FXCollections.observableArrayList();

    private String systemName;
    private Long id;

    @FXML
    public void initialize() {
        tasksListView.setCellFactory(new TaskCardCellFactory());
        tasksListView.setItems(tasksObsList);
    }

    public String getSystemName() {
        return systemName;
    }

    public Long getId() {
        return id;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        columnTitle.setText(title);
    }

    public void addTask(TaskModel task) {
        tasksObsList.add(task);
    }
}
