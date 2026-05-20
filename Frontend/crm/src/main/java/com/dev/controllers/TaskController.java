package com.dev.controllers;

import org.controlsfx.control.SearchableComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class TaskController {
    @FXML
    private SearchableComboBox<String> statusesList;

    @FXML
    private SearchableComboBox<String> respManagersList;

    @FXML
    public void initialize() {
        ObservableList<String> statuses = FXCollections.observableArrayList(
            "В работе",
            "Не начато",
            "Ожидается оплата",
            "Просрочено",
            "Выполнено"
        );

        ObservableList<String> managers = FXCollections.observableArrayList(
            "manager_login",  
            "manager_login_1",  
            "manager_login_2",  
            "manager_login_3",  
            "manager_login_4"  
        );

        statusesList.setItems(statuses);
        respManagersList.setItems(managers);
    }
}
