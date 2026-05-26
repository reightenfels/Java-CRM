package com.dev.controllers;

import org.controlsfx.control.SearchableComboBox;

import com.dev.features.manager.OpenTaskCard.OpenTaskCardUseCase;
import com.dev.services.Navigation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskEditController {
    @FXML
    public Label taskTitleLabel;
    
    @FXML
    public TextField taskTitle;

    @FXML
    public TextArea taskDescription;

    @FXML
    public TextField taskPrice;

    @FXML
    public DatePicker taskDeadline;
    
    @FXML
    public SearchableComboBox<String> statusesList;

    @FXML
    public SearchableComboBox<String> respManagersList;

    public ObservableList<String> statusesObsList = FXCollections.observableArrayList();

    public ObservableList<String> managersObsList = FXCollections.observableArrayList();

    private Long taskId;

    @FXML
    public void initialize() {
        statusesList.setItems(statusesObsList);
        respManagersList.setItems(managersObsList);
    }

    /**
     * Обрабатывает нажатие на кнопку возврата на предыдущую страницу
     */
    public void goBackBtnClick() {
        Navigation.getInstance().loadAndShowBack();
    }

    /**
     * Обрабатывает нажатие на кнопку открытия чата с сотрудниками
     */
    public void handleOpenEmployeeDialogBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/employee-chats.fxml");
    }

    /**
     * Обрабатывает нажатие на кнопку открытия чата с клиентом
     */
    public void handleOpenClientDialogBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/client-chats.fxml");
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
        loadData();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void loadData() {
        if (taskId != null) {
            OpenTaskCardUseCase.execute(this);
        }
    }
}
