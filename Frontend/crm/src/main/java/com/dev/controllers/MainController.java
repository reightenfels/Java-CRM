package com.dev.controllers;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainController {
    @FXML
    private StackPane mainContent;

    @FXML
    public void initialize() throws IOException {
        Node content = FXMLLoader.load(getClass().getResource("/employee-chats.fxml"));
        mainContent.getChildren().add(content);
    }

    /**
     * Проигрывает анимацию смены контента
     * @param current
     * @param next
     */
    public void changePageAnimation(Node current, Node next) {
        mainContent.getChildren().add(next);

        double width = mainContent.getWidth();

        Interpolator interpolator = Interpolator.LINEAR;
        Duration duration = Duration.millis(300);

        current.setTranslateX(0);
        TranslateTransition out = new TranslateTransition(duration, current);
        out.setFromX(0);
        out.setToX(-width);
        out.setInterpolator(interpolator);

        next.setTranslateX(width);
        TranslateTransition in = new TranslateTransition(duration, next);
        in.setFromX(width);
        in.setToX(0);
        in.setInterpolator(interpolator);

        ParallelTransition pt = new ParallelTransition(out, in);

        pt.setOnFinished(eh -> {
            mainContent.getChildren().remove(current);
        });

        pt.play();
    }

    /**
     * Загружает FXML страницу
     * @param fxmlName
     * @return
     */
    private Node loadNode(String fxmlName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlName));
        } catch (IOException e) {
            System.err.println("❌ Ошибка загрузки " + fxmlName + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Загружает FXML и отображает
     * @param fxmlName
     */
    private void loadAndShow(String fxmlName) {
        Node next = loadNode(fxmlName);
        if (next != null) {
            Node current = mainContent.getChildren().get(0);
            changePageAnimation(current, next);
        }
    }

    /**
     * Обрабатывает открытие страницы чатов с сотрудниками
     */
    public void openEmployeeChatsPage() {
        loadAndShow("/employee-chats.fxml");
    }

    /**
     * Обрабатывает открытие страницы чатов с клиентами
     */
    public void openClientChatsPage() {
        loadAndShow("/client-chats.fxml");
    }
}
