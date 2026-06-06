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
import com.dev.features.manager.ChangeTaskStatus.ChangeTaskStatusUseCase;
import com.dev.services.Navigation;
import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    private Button makeTaskOverdueBtn;

    @FXML
    private Button makeTaskCompletedBtn;

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
     * Возвращает модель выбранного диалога
     */
    private DialogModel getSelectedDialog() {
        return dialogsListView.getSelectionModel().getSelectedItem();
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
        DialogModel selectedDialog = getSelectedDialog();

        if (selectedDialog == null) {
            return;
        }

        if (selectedDialog.getTaskSystemName().equals("OVERDUE")) {
            makeTaskOverdueBtn.setVisible(false);
            makeTaskOverdueBtn.setManaged(false);
        }
        else {
            makeTaskOverdueBtn.setVisible(true);
            makeTaskOverdueBtn.setManaged(true);
        }

        if (selectedDialog.getTaskSystemName().equals("COMPLETED")) {
            makeTaskCompletedBtn.setVisible(false);
            makeTaskCompletedBtn.setManaged(false);
        }
        else {
            makeTaskCompletedBtn.setVisible(true);
            makeTaskCompletedBtn.setManaged(true);
        }

        taskButtons.setVisible(true);
        sendMessagePanel.setVisible(true);
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
        if (getSelectedDialog() == null) {
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
                getSelectedDialog().getDialogId()
            )
        );

        messageText.clear();
    }

    /**
     * Обрабатывает клик на кнопку перехода к редактированию задачи
     */
    public void handleGoToTaskBtnClick() {
        DialogModel selectedDialog = getSelectedDialog();

        if (selectedDialog == null) {
            return;
        }

        Navigation.getInstance().loadAndShowForwardTyped(
            "/views/pages/task-edit.fxml",
            TaskEditController.class,
            controller -> controller.setData(selectedDialog.getTaskId(), selectedDialog.getDialogId())
        );
    }

    /**
     * Выбирает диалог по ID
     */
    public void selectDefaultDialogById() {
        Platform.runLater(() -> {
            DialogModel targetDialog = dialogsObsList.stream()
                .filter(dialog -> dialog.getDialogId().equals(this.defaultSelectedDialogId))
                .findFirst()
                .orElse(null);

            if (targetDialog != null) {
                dialogsListView.getSelectionModel().select(targetDialog);
            }
        });
    }

    /**
     * Обрабатывает клик на кнопку изменения статуса задачи на "Просрочено"
     */
    public void setStatusToOverdueHandleClick () {
        makeTaskOverdueBtn.setVisible(false);

        DialogModel selectedDialog = getSelectedDialog();

        if (selectedDialog == null) {
            return;
        }

        try {
            ChangeTaskStatusUseCase.execute(
                "OVERDUE", 
                selectedDialog.getTaskId(), 
                selectedDialog.getTaskVersion()
            );
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Обрабатывает клик на кнопку изменения статуса задачи на "Выполнено"
     */
    public void setStatusToCompletedHandleClick () {
        makeTaskCompletedBtn.setVisible(false);

        DialogModel selectedDialog = getSelectedDialog();

        if (selectedDialog == null) {
            return;
        }

        try {
            ChangeTaskStatusUseCase.execute(
                "COMPLETED", 
                selectedDialog.getTaskId(), 
                selectedDialog.getTaskVersion()
            );
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    public Long getDefaultSelectedDialogId() {
        return defaultSelectedDialogId;
    }

    public void setDefaultSelectedDialogId(Long defaultSelectedDialogId) {
        this.defaultSelectedDialogId = defaultSelectedDialogId;
    }    
}
