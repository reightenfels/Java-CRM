package com.dev.controllers;

import java.time.LocalDateTime;

import com.dev.dto.models.DialogModel;
import com.dev.dto.models.message.ChatItem;
import com.dev.dto.models.message.MessageModel;
import com.dev.factories.DialogCellFactory;
import com.dev.factories.MessageCellFactory;
import com.dev.services.Navigation;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ClientChatsController {
    @FXML
    private ListView<DialogModel> dialogsListView;

    @FXML
    private ListView<ChatItem> messagesListView;

    @FXML
    private TextArea messageText;

    private ObservableList<ChatItem> messagesObjList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dialogsListView.setCellFactory(new DialogCellFactory());
        messagesListView.setCellFactory(new MessageCellFactory());

        

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
                LocalDateTime.now(),
                true,
                ""
            )
        );

        scrollMessagesToBottom();

        messageText.clear();
    }

    /**
     * Обрабатывает клик на кнопку перехода в карточку клиента
     */
    public void handleGoToClientCardBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/client.fxml");
    }

    /**
     * Обрабатывает клик на кнопку перехода в карточку задачи
     */
    public void handleGoToTaskCardBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/task-edit.fxml");
    }
}
