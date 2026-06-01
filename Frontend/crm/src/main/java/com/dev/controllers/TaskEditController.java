package com.dev.controllers;

import com.dev.features.manager.OpenTaskCard.OpenTaskCardUseCase;
import com.dev.features.manager.UpdateTask.UpdateTaskUseCase;
import com.dev.services.Navigation;
import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class TaskEditController extends AbstractTaskController {
    private Long taskId;
    private int version;

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
     * Обрабатывает клик на кнопку сохранения задачи
     */
    public void handelSaveBtnClick() {
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

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
        loadData();
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
}
