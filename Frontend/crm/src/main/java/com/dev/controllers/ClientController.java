package com.dev.controllers;

import com.dev.services.Navigation;

public class ClientController {
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
}
