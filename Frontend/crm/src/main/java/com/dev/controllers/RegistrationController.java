package com.dev.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dev.dto.requests.RegistrationRequest;
import com.dev.dto.responses.errors.RegistrationErrorResponse;
import com.dev.dto.responses.success.RegistrationResponse;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class RegistrationController {
    @FXML
    private StackPane root;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField firstName;

    @FXML 
    private TextField lastName;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private VBox errorPanel;

    @FXML
    private VBox errorsList;

    private TranslateTransition slideIn;
    private TranslateTransition slideOut;

    @FXML
    public void initialize() {
        double panelWidth = root.getPrefWidth();

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
     * Создает объект запроса на регистрацию
     */
    private RegistrationRequest getRegisterRequest() {
        return new RegistrationRequest(
            login.getText(),
            password.getText(),
            firstName.getText(),
            lastName.getText(),
            phone.getText(),
            email.getText()
        );
    }

    /**
     * Отрисовывает ошибки регистрации
     * @param errors - Ошибки регистрации
     */
    private void displayRegisterErrors(Map<String, String> errors) {
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
     * Отображает главный экран приложения
     * @throws IOException
     */
    private void setMainScreen() throws IOException {
        Scene scene = root.getScene();
        scene.setRoot(FXMLLoader.load(getClass().getResource("/main.fxml")));
    }

    /**
     * Обрабатывает нажатие кнопки регистрации
     */
    public void registrationClick() {
        RegistrationRequest registrationRequest = getRegisterRequest();

        try {
            Http
            .request("/api/auth/registration")
            .post(JsonEncoder.encode(registrationRequest))
            .onResponse(response -> {
                String body = response.body();
                System.out.println(body);

                if (response.statusCode() == 200) {
                    try {
                        RegistrationResponse registerResponse = ResponseUnpacker.extract(body, RegistrationResponse.class);
                        
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
                        RegistrationErrorResponse errorResponse = JsonEncoder.decode(body, RegistrationErrorResponse.class);

                        displayRegisterErrors(errorResponse.errors);

                    } catch (JsonProcessingException e) {
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
