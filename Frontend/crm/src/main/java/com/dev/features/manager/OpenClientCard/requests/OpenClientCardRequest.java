package com.dev.features.manager.OpenClientCard.requests;

import com.dev.controllers.ClientController;
import com.dev.dto.models.ClientProfileModel;
import com.dev.dto.responses.success.OpenClientCardResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;

public class OpenClientCardRequest {
    private static OpenClientCardRequest instance = null;

    private OpenClientCardRequest() {}

    public static OpenClientCardRequest getInstance() {
        if (instance == null) {
            instance = new OpenClientCardRequest();
        }
        return instance;
    }

    public void displayData(
        ClientProfileModel clientModel,
        ClientController controller
    ) {
        Platform.runLater(() -> {
            controller.clientLogin.setText(clientModel.getLogin());
            controller.clientFirstName.setText(clientModel.getFirstName());
            controller.clientLastName.setText(clientModel.getLastName());
            controller.clientPhone.setText(clientModel.getPhone());
            controller.clientEmail.setText(clientModel.getEmail());
        });
    }

    public void requestAndDisplay(
        ClientController controller
    ) {
        Http
        .request("/api/clients")
        .auth()
        .get()
        .queryParam("login", controller.getLogin())
        .onResponse(response -> {
            String body = response.body();

            OpenClientCardResponse clientResponse = ResponseUnpacker.extract(body, OpenClientCardResponse.class);

            displayData(
                clientResponse.client,
                controller
            );
        })
        .send();
    }
}
