package com.dev.controllers;

import java.io.IOException;

import com.dev.services.Navigation;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    private StackPane mainContent;

    @FXML
    public void initialize() throws IOException {
        Navigation.getInstance().setMainContent(mainContent);
        Navigation.getInstance().loadStartPage("/views/pages/tasks-list.fxml");
    }

    /**
     * Обрабатывает открытие страницы чатов с сотрудниками
     */
    public void openEmployeeChatsPage() {
        Navigation.getInstance().loadAndShowForward("/views/pages/employee-chats.fxml");
    }

    /**
     * Обрабатывает открытие страницы чатов с клиентами
     */
    public void openClientChatsPage() {
        Navigation.getInstance().loadAndShowForward("/views/pages/client-chats.fxml");
    }

    /**
     * Обрабатывает открытие страницы редактирования профиля
     */
    public void openProfilePage() {
        Navigation.getInstance().loadAndShowForward("/views/pages/profile.fxml");
    }

    /**
     * Обрабатывает открытие страницы задач
     */
    public void openTasksPage() {
        Navigation.getInstance().loadAndShowForward("/views/pages/tasks-list.fxml");
    }

    /**
     * Обрабатывает открытие страницы клиентов
     */
    public void openClientsPage() {
        Navigation.getInstance().loadAndShowForward("/views/pages/clients-list.fxml");
    }

    /**
     * Обрабатывает открытие страницы сотрудников
     */
    public void openEmployeesPage() {
        Navigation.getInstance().loadAndShowForward("/views/pages/employees-list.fxml");
    }

    /**
     * Обрабатывает открытие страницы создания задачи
     */
    public void openCreateTaskPage() {
        Navigation.getInstance().loadAndShowForward("/views/pages/task-create.fxml");
    }
}
