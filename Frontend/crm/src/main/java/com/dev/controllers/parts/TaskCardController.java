package com.dev.controllers.parts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.dev.controllers.ClientController;
import com.dev.controllers.ProfileController;
import com.dev.controllers.TaskEditController;
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

    private Long id;

    public void setData(
        String taskTitle,
        Long taskPrice,
        String taskManagerLogin,
        LocalDateTime taskDeadline,
        String taskClientLogin,
        Long id
    ) {
        this.taskTitle.setText(taskTitle);
        this.taskPrice.setText(String.valueOf(taskPrice));
        this.taskManagerLogin.setText(taskManagerLogin);
        this.taskDeadline.setText(taskDeadline.format(DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm")));
        this.taskClientLogin.setText(taskClientLogin);
        this.id = id;
    }

    /**
     * Обрабатывает нажатие на кнопку открытия задачи
     */
    public void handleOpenTaskBtnClik() {
        Navigation.getInstance().loadAndShowForwardTyped(
            "/views/pages/task-edit.fxml",
            TaskEditController.class,
            controller -> controller.setTaskId(id)
        );
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
        Navigation.getInstance().loadAndShowForwardTyped(
            "/views/pages/profile.fxml",
            ProfileController.class,
            controller -> controller.setLogin(taskManagerLogin.getText())
        );
    }

    /**
     * Обрабатывает нажатие на кнопку открытия данных клиента
     */
    public void handleOpenClientBtnClick() {
        Navigation.getInstance().loadAndShowForwardTyped(
            "/views/pages/client.fxml",
            ClientController.class,
            controller -> controller.setLogin(taskClientLogin.getText())
        );
    }
}
