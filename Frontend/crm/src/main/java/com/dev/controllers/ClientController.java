package com.dev.controllers;

import com.dev.features.manager.OpenClientCard.OpenClientCardUseCase;
import com.dev.services.Navigation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientController {
    @FXML
    public Label clientLogin;

    @FXML
    public TextField clientFirstName;

    @FXML
    public TextField clientLastName;

    @FXML
    public TextField clientPhone;

    @FXML
    public TextField clientEmail;

    private String login;

    /**
     * Обработчик перехода в чат с клиентом
     */
    public void handleGoToClientChatBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/client-chats.fxml");
    }

    /**
     * Обработчик клика на кнопку возврата на предыдущую страницу
     */
    public void goBackBtnClick() {
        Navigation.getInstance().loadAndShowBack();
    }

    /**
     * Загружает данные клиента
     */
    public void loadData() {
        if (this.login != null) {
            OpenClientCardUseCase.execute(this);
        }
    }

    public void setLogin(String login) {
        this.login = login;

        loadData();
    }

    public String getLogin() {
        return login;
    }
}
