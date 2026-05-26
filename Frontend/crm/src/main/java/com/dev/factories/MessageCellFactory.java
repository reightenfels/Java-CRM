package com.dev.factories;

import java.io.IOException;

import com.dev.controllers.parts.MessageController;
import com.dev.dto.models.message.ChatItem;
import com.dev.dto.models.message.MessageModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MessageCellFactory implements Callback<ListView<ChatItem>, ListCell<ChatItem>> {
    @Override
    public ListCell<ChatItem> call(ListView<ChatItem> param) {
        return new ListCell<>() {
            private Parent cellTemplate;
            private MessageController cellController;

            @Override
            protected void updateItem(ChatItem item, boolean empty) {
                super.updateItem(item, empty);

                getStyleClass().remove("myMessage");

                if (empty || item == null) {
                    setGraphic(null);
                    return;
                }

                if (item instanceof MessageModel) {
                    renderMessage(item);
                }
            }

            private void renderMessage(ChatItem item) {
                if (cellTemplate == null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(MessageController.class.getResource("/views/parts/message.fxml"));
                        cellTemplate = loader.load();
                        cellController = loader.getController();
                    } catch (IOException e) {
                        throw new RuntimeException("Ошибка загрузки шаблона диалога", e);
                    }
                }

                cellController.setData(
                    ((MessageModel) item).getText(), 
                    ((MessageModel) item).getSender()
                );

                if (((MessageModel) item).isMe()) {
                    getStyleClass().add("myMessage");
                }

                setGraphic(cellTemplate);
            }
        };
    }
}
