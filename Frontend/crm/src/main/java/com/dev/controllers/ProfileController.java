package com.dev.controllers;

import com.dev.features.employee.OpenEmployeeProfile.OpenEmployeeProfileUseCase;
import com.dev.services.Navigation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfileController {
    @FXML
    public Label employeeLogin;

    @FXML
    public TextField employeeFirstName;

    @FXML
    public TextField employeeLastName;

    @FXML
    public TextField employeePhone;

    @FXML
    public TextField employeeEmail;

    @FXML
    public PasswordField employeeNewPassword;

    private Long id;
    private String login;

    /**
     * Обрабатывает клик на кнопку возвращата на предыдущую страницу
     */
    public void goBackBtnClick() {
        Navigation.getInstance().loadAndShowBack();
    }

    /**
     * Обрабатывает загрузку данных сотрудника
     */
    private void loadData() {
        if (this.login != null) {
            OpenEmployeeProfileUseCase.execute(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
        loadData();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
