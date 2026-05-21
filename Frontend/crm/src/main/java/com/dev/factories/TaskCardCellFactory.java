package com.dev.factories;

import java.io.IOException;

import com.dev.controllers.parts.DialogController;
import com.dev.controllers.parts.TaskCardController;
import com.dev.dto.models.TaskModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TaskCardCellFactory implements Callback<ListView<TaskModel>, ListCell<TaskModel>> {
    @Override
    public ListCell<TaskModel> call(ListView<TaskModel> param) {
        return new ListCell<>() {
            private Parent cellTemplate;
            private TaskCardController cellController;

            @Override
            protected void updateItem(TaskModel item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    return;
                }

                if (cellTemplate == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(DialogController.class.getResource("/views/parts/task-card.fxml"));
                        cellTemplate = loader.load();
                        cellController = loader.getController();
                    } catch (IOException e) {
                        throw new RuntimeException("Ошибка загрузки шаблона диалога", e);
                    }
                }

                
            }
        };
    }
}
