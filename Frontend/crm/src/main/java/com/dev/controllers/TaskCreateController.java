package com.dev.controllers;
import com.dev.features.manager.CreateTask.CreateTaskUseCase;
import com.dev.features.manager.OpenCreateTaskCard.OpenCreateTaskCardUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class TaskCreateController extends AbstractTaskController {
    /**
     * Обрабатывает клик на кнопку создания задачи
     */
    public void handelSaveBtnClick() {
        try {
            CreateTaskUseCase.execute(this);
        } catch (JsonProcessingException e) {
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
     * Загружает данные
     */
    public void loadData() {
        OpenCreateTaskCardUseCase.execute(this);
    }
}
