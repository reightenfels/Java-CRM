package com.dev.controllers;

import com.dev.dto.models.DialogModel;
import com.dev.dto.models.message.ChatItem;
import com.dev.dto.models.message.MessageModel;
import com.dev.factories.DialogCellFactory;
import com.dev.factories.MessageCellFactory;
import com.dev.features.employee.OpenTasksDialogsList.OpenTasksDialogsListUseCase;
import com.dev.services.Navigation;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private final ObservableList<DialogModel> dialogsObsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dialogsListView.setCellFactory(new DialogCellFactory());
        messagesListView.setCellFactory(new MessageCellFactory());

        dialogsListView.setItems(dialogsObsList);

        OpenTasksDialogsListUseCase.execute(dialogsObsList);

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

    /**
     * Обрабатывает клик на кнопку перехода к задаче
     */
    public void handleGoToTaskBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/task-edit.fxml");
    }
}
