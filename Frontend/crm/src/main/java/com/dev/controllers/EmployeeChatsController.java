package com.dev.controllers;

import java.time.LocalDateTime;

import com.dev.dto.models.DialogModel;
import com.dev.dto.models.message.ChatItem;
import com.dev.dto.models.message.MessageModel;
import com.dev.dto.requests.SendMessageToTaskRequest;
import com.dev.factories.DialogCellFactory;
import com.dev.factories.MessageCellFactory;
import com.dev.features.employee.OpenTaskDialog.OpenTaskDialogUseCase;
import com.dev.features.employee.OpenTasksDialogsList.OpenTasksDialogsListUseCase;
import com.dev.features.employee.SendMessageToTask.SendMessageToTaskUseCase;
import com.dev.services.Navigation;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class EmployeeChatsController {
    @FXML
    private ListView<DialogModel> dialogsListView;

    @FXML
    private ListView<ChatItem> messagesListView;

    @FXML
    private TextArea messageText;

    @FXML
    private HBox taskButtons;

    @FXML
    private HBox sendMessagePanel;

    public final ObservableList<DialogModel> dialogsObsList = FXCollections.observableArrayList();
    private final ObservableList<ChatItem> messagesObsList = FXCollections.observableArrayList();

    private Long defaultSelectedDialogId;

    @FXML
    public void initialize() {
        dialogsListView.setCellFactory(new DialogCellFactory());
        messagesListView.setCellFactory(new MessageCellFactory());

        dialogsListView.setItems(dialogsObsList);
        messagesListView.setItems(messagesObsList);

        OpenTasksDialogsListUseCase.execute(this);

        scrollMessagesToBottom();
        selectDialog();
        hideIfDialogNotSelected();
    }

    /**
     * Скрывает все нужные элементы если не выбран диалог
     */
    private void hideIfDialogNotSelected() {
        taskButtons.setVisible(false);
        sendMessagePanel.setVisible(false);
    }

    /**
     * Показывает все нужные элементы если выбран диалог
     */
    private void showIfDialogSelected() {
        taskButtons.setVisible(true);
        sendMessagePanel.setVisible(true);
    }

    /**
     * Возвращает ID текущего диалога
     */
    private Long getSelectedDialogId() {
        return dialogsListView.getSelectionModel().getSelectedItem() != null ?
            dialogsListView.getSelectionModel().getSelectedItem().getDialogId() :
            null;
    }

    /**
     * Обрабатывает выбор диалога
     */
    private void selectDialog() {
        dialogsListView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, dialog) -> {
                messagesObsList.clear();

                if (dialog != null) {
                    showIfDialogSelected();
                    OpenTaskDialogUseCase.execute(dialog.getDialogId(), messagesObsList);
                }
                else {
                    hideIfDialogNotSelected();
                }
            }
        );
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
        if (getSelectedDialogId() == null) {
            return;
        }

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

        SendMessageToTaskUseCase.execute(
            new SendMessageToTaskRequest(
                newMessageText,
                getSelectedDialogId()
            )
        );

        messageText.clear();
    }

    /**
     * Обрабатывает клик на кнопку перехода к задаче
     */
    public void handleGoToTaskBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/task-edit.fxml");
    }

    /**
     * Выбирает диалог по ID
     */
    public void selectDefaultDialogById() {
        DialogModel targetDialog = dialogsObsList.stream()
            .filter(dialog -> dialog.getDialogId().equals(this.defaultSelectedDialogId))
            .findFirst()
            .orElse(null);

        if (targetDialog != null) {
            dialogsListView.getSelectionModel().select(targetDialog);
        }
    }

    public Long getDefaultSelectedDialogId() {
        return defaultSelectedDialogId;
    }

    public void setDefaultSelectedDialogId(Long defaultSelectedDialogId) {
        this.defaultSelectedDialogId = defaultSelectedDialogId;
    }    
}
