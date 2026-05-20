package com.dev.controllers;

import com.dev.dto.models.ClientModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class ClientsListController {
    @FXML
    private TableView<ClientModel> clientsTableView;

    @FXML
    private TableColumn<ClientModel, String> photoColumn;

    @FXML
    private TableColumn<ClientModel, String> fioColumn;

    @FXML
    private TableColumn<ClientModel, String> loginColumn;

    @FXML
    private TableColumn<ClientModel, String> emailColumn;

    @FXML
    private TableColumn<ClientModel, String> phoneColumn;

    @FXML
    private TableColumn<ClientModel, Void> actionsColumn;

    private final ObservableList<ClientModel> clientsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        fioColumn.setCellValueFactory(data -> data.getValue().fioProperty());
        loginColumn.setCellValueFactory(data -> data.getValue().loginProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().emailProperty());
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());
        setupActionColumn();

        clientsTableView.setItems(clientsList);

        for (int i = 0; i < 3; i++) {
            clientsList.add(new ClientModel(
                "Photo",
                "Иванов Иван Иванович",
                "login",
                "test@mai.ru",
                "+7 (961) 123 12 12"
            ));
        }
    }

    private void setupActionColumn() {
        actionsColumn.setCellFactory(column -> new TableCell<ClientModel, Void>() {
            private final Button chatButton = new Button("В чат");
            private final Button openButton = new Button("Открыть");
            private final HBox buttonsPane = new HBox(10, chatButton, openButton);
            
            {
                chatButton.getStyleClass().add("client-table-btn-chat");
                chatButton.setCursor(Cursor.HAND);

                openButton.getStyleClass().add("client-table-btn-open");
                openButton.setCursor(Cursor.HAND);
                
                chatButton.setOnAction(event -> {
                    // ClientModel client = getTableView().getItems().get(getIndex());
                    // handleChatClick(client);
                });
                
                openButton.setOnAction(event -> {
                    // ClientModel client = getTableView().getItems().get(getIndex());
                    // handleOpenClick(client);
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    buttonsPane.setAlignment(Pos.CENTER);
                    setGraphic(buttonsPane);
                }
            }
        });
    }
}
