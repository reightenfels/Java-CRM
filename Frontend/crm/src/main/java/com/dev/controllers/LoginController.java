package com.dev.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dev.dto.requests.LoginRequest;
import com.dev.dto.responses.errors.ErrorResponse;
import com.dev.dto.responses.success.LoginResponse;
import com.dev.services.Http;
import com.dev.services.JsonEncoder;
import com.dev.services.JwtTokens;
import com.dev.services.ResponseUnpacker;
import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LoginController {
    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private StackPane loginRoot;

    @FXML
    private VBox errorPanel;

    @FXML
    private VBox errorsList;

    private TranslateTransition slideIn;
    private TranslateTransition slideOut;

    @FXML
    public void initialize() {
        double panelWidth = loginRoot.getPrefWidth();

        errorPanel.setTranslateX(panelWidth);

        // Анимация появления (справа → влево)
        slideIn = new TranslateTransition(Duration.millis(450), errorPanel);
        slideIn.setFromX(panelWidth);
        slideIn.setToX(0);
        slideIn.setInterpolator(javafx.animation.Interpolator.EASE_OUT);

        // Анимация скрытия (слева → вправо)
        slideOut = new TranslateTransition(Duration.millis(450), errorPanel);
        slideOut.setFromX(0);
        slideOut.setToX(panelWidth);
        slideOut.setInterpolator(javafx.animation.Interpolator.EASE_IN);
    }

    /**
     * Возвращает объект запроса на авторизацию
     * @return
     */
    private LoginRequest getLoginRequest() {
        return new LoginRequest(
            login.getText(),
            password.getText()
        );
    }

    /**
     * Отображает главный экран приложения
     * @throws IOException
     */
    private void setMainScreen() throws IOException {
        Scene scene = loginRoot.getScene();
        scene.setRoot(FXMLLoader.load(getClass().getResource("/main.fxml")));
    }

    /**
     * Отрисовывает ошибки регистрации
     * @param errors - Ошибки регистрации
     */
    private void displayLoginErrors(Map<String, String> errors) {
        Platform.runLater(
            () -> {
                errorsList.getChildren().clear();

                List<Label> errorsLabelsList = new ArrayList<>();

                for (String error : errors.values()) {
                    Label errorLabel = new Label(error);
                    errorLabel.getStyleClass().add("errorRegisterFormLable");
                    errorsLabelsList.add(errorLabel);
                }
                errorsList.getChildren().setAll(errorsLabelsList);

                slideOut.stop();
                errorPanel.setTranslateX(errorPanel.getPrefWidth());
                slideIn.play();
            }
        );
    }

    /**
     * Обрабатывает нажатие на кнопку авторизации
     */
    public void loginClick() {
        LoginRequest request = getLoginRequest();
        
        try {
            Http
            .request("/api/auth/login")
            .post(JsonEncoder.encode(request))
            .onResponse(response -> {
                String body = response.body();
                System.out.println(body);

                if (response.statusCode() == 200) {
                    try {
                        LoginResponse registerResponse = ResponseUnpacker.extract(body, LoginResponse.class);

                        JwtTokens.accessToken = registerResponse.accessToken;
                        JwtTokens.refreshToken = registerResponse.refreshToken;

                        setMainScreen();
                    }
                    catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    try {
                        ErrorResponse errorResponse = ResponseUnpacker.extract(body, ErrorResponse.class);
                        displayLoginErrors(errorResponse.errors);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            })
            .send();
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}
