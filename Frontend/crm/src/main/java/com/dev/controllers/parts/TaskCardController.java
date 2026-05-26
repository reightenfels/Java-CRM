package com.dev.controllers.parts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.dev.services.Navigation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskCardController {
    @FXML
    private Label taskTitle;

    @FXML
    private Label taskPrice;

    @FXML
    private Label taskManagerLogin;

    @FXML
    private Label taskDeadline;

    @FXML
    private Label taskClientLogin;

    public void setData(
        String taskTitle,
        Long taskPrice,
        String taskManagerLogin,
        LocalDateTime taskDeadline,
        String taskClientLogin
    ) {
        this.taskTitle.setText(taskTitle);
        this.taskPrice.setText(String.valueOf(taskPrice));
        this.taskManagerLogin.setText(taskManagerLogin);
        this.taskDeadline.setText(taskDeadline.format(DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm")));
        this.taskClientLogin.setText(taskClientLogin);
    }

    /**
     * Обрабатывает нажатие на кнопку открытия задачи
     */
    public void handleOpenTaskBtnClik() {
        Navigation.getInstance().loadAndShowForward("/views/pages/task-edit.fxml");
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

    /**
     * Обрабатывает нажатие на кнопку открытия данных ответственного менеджера
     */
    public void handleOpenRespManagerBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/profile.fxml");
    }

    /**
     * Обрабатывает нажатие на кнопку открытия данных клиента
     */
    public void handleOpenClientBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/profile.fxml");
    }
}
