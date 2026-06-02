package com.dev.controllers;

import com.dev.dto.models.EmployeeModel;
import com.dev.services.Navigation;

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

public class EmployeesListController {
    @FXML
    private TableView<EmployeeModel> employeesTableView;

    @FXML
    private TableColumn<EmployeeModel, String> photoColumn;

    @FXML
    private TableColumn<EmployeeModel, String> fioColumn;

    @FXML
    private TableColumn<EmployeeModel, String> loginColumn;

    @FXML
    private TableColumn<EmployeeModel, String> emailColumn;

    @FXML
    private TableColumn<EmployeeModel, String> phoneColumn;

    @FXML
    private TableColumn<EmployeeModel, Void> actionsColumn;

    private final ObservableList<EmployeeModel> employeesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        fioColumn.setCellValueFactory(data -> data.getValue().fioProperty());
        loginColumn.setCellValueFactory(data -> data.getValue().loginProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().emailProperty());
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());
        setupActionColumn();

        employeesTableView.setItems(employeesList);

        for (int i = 0; i < 3; i++) {
            employeesList.add(new EmployeeModel(
                "Photo",
                "Иванов Иван Иванович",
                "employee" + (i + 1),
                "employee" + (i + 1) + "@mail.ru",
                "+7 (961) 123 12 12"
            ));
        }
    }

    private void setupActionColumn() {
        actionsColumn.setCellFactory(column -> new TableCell<EmployeeModel, Void>() {
            private final Button chatButton = new Button("В чат");
            private final Button openButton = new Button("Открыть");
            private final HBox buttonsPane = new HBox(10, chatButton, openButton);
            
            {
                chatButton.getStyleClass().add("client-table-btn-chat");
                chatButton.setCursor(Cursor.HAND);

                openButton.getStyleClass().add("client-table-btn-open");
                openButton.setCursor(Cursor.HAND);
                
                chatButton.setOnAction(event -> {
                    Navigation.getInstance().loadAndShowForward("/views/pages/employee-chats.fxml");
                });
                
                openButton.setOnAction(event -> {
                    EmployeeModel employee = getTableView().getItems().get(getIndex());
                    String employeeLogin = employee.getLogin();

                    Navigation.getInstance().loadAndShowForwardTyped(
                        "/views/pages/profile.fxml",
                        ProfileController.class,
                        controller -> controller.setLogin(employeeLogin)
                    );
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

    /**
     * Обработчик клика на кнопку создания сотрудника
     */
    public void handleCreateEmployeeBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/employee-create.fxml");
    }
}
