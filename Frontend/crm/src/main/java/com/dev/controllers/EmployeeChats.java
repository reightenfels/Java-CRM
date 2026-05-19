package com.dev.controllers;

import com.dev.dto.models.DialogModel;
import com.dev.factories.DialogCellFactory;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class EmployeeChats {
    @FXML
    private ListView<DialogModel> dialogsListView;

    @FXML
    public void initialize() {
        dialogsListView.setCellFactory(new DialogCellFactory());
        System.out.println("Employee chats");

        for (int i = 0; i < 3; i++) {
            dialogsListView.getItems().add(
                new DialogModel(
                    "Чат " + i,
                    "01.01.2025 10:" + String.format("%02d", i % 60),
                    "Сообщение " + i
                )
            );
        }
    }
}
