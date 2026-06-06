package com.dev.factories;

import java.io.IOException;

import com.dev.controllers.parts.DialogController;
import com.dev.controllers.parts.TaskCardController;
import com.dev.dto.models.TaskCardModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;

public class TaskCardCellFactory implements Callback<ListView<TaskCardModel>, ListCell<TaskCardModel>> {
    private static final DataFormat TASK_CARD_FORMAT = new DataFormat("application/task-card");

    @Override
    public ListCell<TaskCardModel> call(ListView<TaskCardModel> param) {
        return new ListCell<>() {
            private Parent cellTemplate;
            private TaskCardController cellController;

            @Override
            protected void updateItem(TaskCardModel item, boolean empty) {
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

                cellController.setData(
                    item.getTitle(),
                    item.getPrice() / 100,
                    item.getManagerLogin(),
                    item.getDeadline(),
                    item.getClientLogin(),
                    item.getId(),
                    item.getEmployeeDialogId()
                );

                setOnDragDetected(event -> {                  
                    Dragboard db = startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    
                    content.put(TASK_CARD_FORMAT, item.getId());

                    db.setContent(content);
                    
                    setOpacity(0.5);
                    event.consume();
                });

                setOnDragDone(event -> {
                    setOpacity(1.0);

                    // if (event.getTransferMode() == TransferMode.MOVE) {
                    //     ListView<TaskCardModel> sourceList = getListView();

                    //     if (sourceList != null) {
                    //         sourceList.getItems().remove(item);
                    //     }
                    // }
                    
                    event.consume();
                });

                setGraphic(cellTemplate);
            }
        };
    }

    public static DataFormat getTaskCardFormat() {
        return TASK_CARD_FORMAT;
    }
}
