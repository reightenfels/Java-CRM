package com.dev.controllers.parts;

import com.dev.controllers.TasksListController;
import com.dev.dto.models.TaskCardModel;
import com.dev.factories.TaskCardCellFactory;
import com.dev.features.manager.ChangeTaskStatus.ChangeTaskStatusUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class TaskColumnController {
    @FXML
    private Label columnTitle;

    @FXML
    private ListView<TaskCardModel> tasksListView;

    private final ObservableList<TaskCardModel> tasksObsList = FXCollections.observableArrayList();

    private TasksListController tasksListController;

    private String systemName;
    private Long id;

    @FXML
    public void initialize() {
        tasksListView.setCellFactory(new TaskCardCellFactory());
        tasksListView.setItems(tasksObsList);

        tasksListView.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasContent(TaskCardCellFactory.getTaskCardFormat())) {

                event.acceptTransferModes(TransferMode.MOVE);
                event.consume();
            }
        });

        tasksListView.setOnDragEntered(event -> {
            if (event.getDragboard().hasContent(TaskCardCellFactory.getTaskCardFormat())) {
                tasksListView.setStyle("-fx-opacity: 0.7");
            }
        });

        tasksListView.setOnDragExited(event -> {
            tasksListView.setStyle("-fx-opacity: 1");
        });

        tasksListView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasContent(TaskCardCellFactory.getTaskCardFormat())) {
                Long taskId = (Long) db.getContent(TaskCardCellFactory.getTaskCardFormat());

                TaskCardModel task = tasksListController.tasksCardsMap.get(taskId);
                

                if (task != null) {
                    if (tasksObsList.contains(task)) {
                        event.setDropCompleted(false);
                        event.consume();
                        return;
                    }

                    if (!tasksObsList.contains(task)) {
                        tasksObsList.add(task);
                    }

                    success = true;

                    try {
                        ChangeTaskStatusUseCase.execute(this.systemName, taskId, task.getVersion());
                    } catch (JsonProcessingException e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
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

    public void addTask(TaskCardModel task) {
        tasksObsList.add(task);
    }

    public TasksListController getTasksListController() {
        return tasksListController;
    }

    public void setTasksListController(TasksListController tasksListController) {
        this.tasksListController = tasksListController;
    }
}
