package com.dev.services;

import java.io.IOException;
import java.util.Stack;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Navigation {
    private static Navigation instance;
    private StackPane mainContent;
    private final Stack<String> navigationStack = new Stack<>();
    private final Interpolator interpolator = Interpolator.LINEAR;
    private final Duration duration = Duration.millis(400);

    private Navigation() {}

    public static synchronized Navigation getInstance() {
        if (instance == null) {
            instance = new Navigation();
        }

        return instance;
    }

    /**
     * Отображает стартовую страницу
     * @param fxmlName
     */
    public void loadStartPage(String fxmlName) throws IOException {
        Node content = FXMLLoader.load(getClass().getResource(fxmlName));
        mainContent.getChildren().add(content);
        navigationStack.push(fxmlName);
    }

    /**
     * Проигрывает анимацию смены контента на следующую страницу
     * @param current
     * @param next
     */
    private void changePageAnimationForward(Node current, Node next) {
        mainContent.getChildren().add(next);

        double width = mainContent.getWidth();

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
     * Проигрывает анимацию смены контента на предыдущую страницу
     * @param current
     * @param prev
     */
    private void changePageAnimationBack(Node current, Node prev) {
        mainContent.getChildren().add(prev);

        double width = mainContent.getWidth();

        current.setTranslateX(0);
        TranslateTransition out = new TranslateTransition(duration, current);
        out.setFromX(0);
        out.setToX(width);
        out.setInterpolator(interpolator);

        prev.setTranslateX(-width);
        TranslateTransition in = new TranslateTransition(duration, prev);
        in.setFromX(-width);
        in.setToX(0);
        in.setInterpolator(interpolator);

        ParallelTransition pt = new ParallelTransition(out, in);

        pt.setOnFinished(eh -> {
            mainContent.getChildren().remove(current);
        });

        pt.play();
    }

    /**
     * Задает контейнер основного контента
     * @param mainContent
     */
    public void setMainContent(StackPane mainContent) {
        this.mainContent = mainContent;
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
     * Загружает и отображает FXML следующей страницы
     * @param fxmlName
     */
    public void loadAndShowForward(String fxmlName) {
        Node next = loadNode(fxmlName);
        if (next != null) {
            Node current = mainContent.getChildren().get(0);

            if (navigationStack.isEmpty() || !fxmlName.equals(navigationStack.peek())) {
                navigationStack.push(fxmlName);
            }

            changePageAnimationForward(current, next);
        }
    }

    /**
     * Загружает и отображает FXML предыдущей страницы
     * @param fxmlName
     */
    public void loadAndShowBack() {
        if (navigationStack.size() <= 1) {
            return;
        }

        navigationStack.pop();
        String fxmlName = navigationStack.peek();

        Node prev = loadNode(fxmlName);
        if (prev != null) {
            Node current = mainContent.getChildren().get(0);
            navigationStack.push(fxmlName);
            changePageAnimationBack(current, prev);
        }
    }
}
