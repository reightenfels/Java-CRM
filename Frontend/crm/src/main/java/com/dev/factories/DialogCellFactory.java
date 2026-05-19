package com.dev.factories;

import java.io.IOException;

import com.dev.controllers.parts.DialogController;
import com.dev.dto.models.DialogModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class DialogCellFactory implements Callback<ListView<DialogModel>, ListCell<DialogModel>> {
    @Override
    public ListCell<DialogModel> call(ListView<DialogModel> param) {
        return new ListCell<>() {
            private Parent cellTemplate;
            private DialogController cellController;

            @Override
            protected void updateItem(DialogModel item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    return;
                }

                // Загружаем FXML для каждой ячейки (не кэшируем)
                if (cellTemplate == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(DialogController.class.getResource("/parts/dialog.fxml"));
                        cellTemplate = loader.load();
                        cellController = loader.getController();
                    } catch (IOException e) {
                        throw new RuntimeException("Ошибка загрузки шаблона диалога", e);
                    }
                }

                // Обновляем данные в контроллере
                cellController.setData(item.getChatName(), item.getTimestamp(), item.getMessage());
                setGraphic(cellTemplate);

                if (isSelected()) {
                    setStyle("-fx-background-color: #3E88D1");
                } 
                else {
                    setStyle("-fx-background-color: #261E1E");
                }

                // Наведение курсора
                setOnMouseEntered(e -> {
                    setStyle("-fx-background-color: #3E88D1");
                    setCursor(Cursor.HAND);
                });

                // Уведение курсора
                setOnMouseExited(e -> {
                    if (!isSelected()) {
                        setStyle("-fx-background-color: #261E1E");
                    }                    
                });
            }
        };
    }
}
