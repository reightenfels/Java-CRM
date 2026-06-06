package com.dev.controllers;

import com.dev.features.manager.DeleteTask.DeleteTaskUseCase;
import com.dev.features.manager.OpenTaskCard.OpenTaskCardUseCase;
import com.dev.features.manager.UpdateTask.UpdateTaskUseCase;
import com.dev.services.Navigation;
import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class TaskEditController extends AbstractTaskController {
    private Long taskId;
    private int version;
    private Long taskDialogId;

    /**
     * Обрабатывает нажатие на кнопку открытия чата с сотрудниками
     */
    public void handleOpenEmployeeDialogBtnClick() {
        Navigation.getInstance().loadAndShowForwardTyped(
            "/views/pages/employee-chats.fxml",
            EmployeeChatsController.class,
            controller -> controller.setDefaultSelectedDialogId(taskDialogId)
        );
    }

    /**
     * Обрабатывает нажатие на кнопку открытия чата с клиентом
     */
    public void handleOpenClientDialogBtnClick() {
        Navigation.getInstance().loadAndShowForward("/views/pages/client-chats.fxml");
    }

    /**
     * Обрабатывает клик на кнопку сохранения задачи
     */
    public void handleSaveBtnClick() {
        try {
            UpdateTaskUseCase.execute(this);
        }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Показывает модальное окно успеха обновления
     */
    public void showSuccessUpdateModal() {
        slideIn.setFromX(rootWidth);
        slideIn.setToX(0);
        slideIn.setInterpolator(javafx.animation.Interpolator.EASE_OUT);
        slideIn.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(3));

        pause.setOnFinished(event -> {
            slideOut.setFromX(0);
            slideOut.setToX(rootWidth);
            slideOut.setInterpolator(javafx.animation.Interpolator.EASE_IN);
            slideOut.play();
        });

        pause.play();        
    }

    /**
     * Обрабатывает клик на кнопку удаления задачи
     */
    public void handleDeleteBtnClick() {
        try {
            DeleteTaskUseCase.execute(this);
        }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Задает все данные, необходимые для корректного отображения
     */
    public void setData(
        Long taskId,
        Long taskDialogId
    ) {
        this.taskId = taskId;
        this.taskDialogId = taskDialogId;
        loadData();
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void loadData() {
        if (taskId != null) {
            OpenTaskCardUseCase.execute(this);
        }
    }

    public Long getTaskDialogId() {
        return taskDialogId;
    }
}
