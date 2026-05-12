package com.dev.controllers;

import com.dev.dto.requests.LoginRequest;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController {
    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private StackPane loginRoot;

    private LoginRequest getLoginRequest() {
        return new LoginRequest(
            login.getText(),
            password.getText()
        );
    }

    

    public void loginClick() {
        double width = loginRoot.getWidth();

        loginRoot.setTranslateX(0);

        TranslateTransition out = new TranslateTransition(Duration.millis(500), loginRoot);
        out.setFromX(0);
        out.setToX(-width);
        out.setInterpolator(Interpolator.EASE_OUT);
        out.play();

        out.setOnFinished(e -> {
            System.out.println(123);
        });

        // LoginRequest request = getLoginRequest();
        
        // try {
        //     Http
        //     .request("/api/auth/login")
        //     .post(JsonEncoder.encode(request))
        //     .onResponse(response -> {
        //         String body = response.body();

        //         if (response.statusCode() == 200) {
        //             TypeReference<ApiResponse<LoginResponse>> typeRef = new TypeReference<>() {};

        //             try {
        //                 ApiResponse<LoginResponse> registerResponse = JsonEncoder.decodeApiResponse(body, typeRef);

        //                 JwtTokens.accessToken = registerResponse.data.accessToken;
        //                 JwtTokens.refreshToken = registerResponse.data.refreshToken;
        //             } catch (JsonProcessingException e) {
        //                 System.out.println(e.getMessage());
        //             }
        //         }
        //     })
        //     .send();
        // } catch (JsonProcessingException e) {
        //     System.out.println(e.getMessage());
        // }
    }
}
