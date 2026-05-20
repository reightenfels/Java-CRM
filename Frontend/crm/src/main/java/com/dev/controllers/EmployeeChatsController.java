package com.dev.controllers;

import com.dev.dto.models.DialogModel;
import com.dev.dto.models.message.ChatItem;
import com.dev.dto.models.message.MessageModel;
import com.dev.factories.DialogCellFactory;
import com.dev.factories.MessageCellFactory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class EmployeeChatsController {
    @FXML
    private ListView<DialogModel> dialogsListView;

    @FXML
    private ListView<ChatItem> messagesListView;

    @FXML
    private TextArea messageText;

    @FXML
    public void initialize() {
        dialogsListView.setCellFactory(new DialogCellFactory());
        messagesListView.setCellFactory(new MessageCellFactory());

        for (int i = 0; i < 3; i++) {
            dialogsListView.getItems().add(
                new DialogModel(
                    "Чат " + i,
                    "01.01.2025 10:" + String.format("%02d", i % 60),
                    "Сообщение " + i
                )
            );
        }

        for (int i = 0; i < 3; i++) {
            messagesListView.getItems().add(
                new MessageModel(
                    "Текст сообщения " + i,
                    "Отправитель " + i,
                    "18:30",
                    false,
                    ""
                )
            );
        }

        for (int i = 0; i < 30; i++) {
            messagesListView.getItems().add(
                new MessageModel(
                    "Текст сообщения " + i,
                    "Отправитель " + i,
                    "18:30",
                    true,
                    ""
                )
            );
        }

        scrollMessagesToBottom();
    }

    /**
     * Скроллит список сообщений в самый низ
     */
    private void scrollMessagesToBottom() {
        Platform.runLater(() -> {
            int lastIndex = messagesListView.getItems().size() - 1;

            messagesListView.scrollTo(lastIndex);
            Platform.runLater(() -> messagesListView.scrollTo(lastIndex));
        });
    }

    /**
     * Обрабатывает клик на кнопку отправки сообщения
     */
    public void sendMessageClick() {
        String newMessageText = messageText.getText().stripTrailing();

        if (newMessageText.isEmpty()) {
            return;
        }

        String newMessageSender = "Текущий сотрудник";

        messagesListView.getItems().add(
            new MessageModel(
                newMessageText,
                newMessageSender,
                "18:30",
                true,
                ""
            )
        );

        scrollMessagesToBottom();

        messageText.clear();
    }
}
