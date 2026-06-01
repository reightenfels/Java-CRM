package com.dev.features.employee.OpenEmployeeProfile.requests;

import com.dev.controllers.ProfileController;
import com.dev.dto.models.ProfileModel;
import com.dev.dto.responses.success.OpenEmployeeProfileResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;

public class OpenEmployeeProfileRequest {
    private static OpenEmployeeProfileRequest instance = null;

    private OpenEmployeeProfileRequest() {}

    public static OpenEmployeeProfileRequest getInstance() {
        if (instance == null) {
            instance = new OpenEmployeeProfileRequest();
        }
        return instance;
    }

    public void displayData(
        ProfileModel profileModel,
        ProfileController controller
    ) {
        Platform.runLater(() -> {
            controller.employeeFirstName.setText(profileModel.getFirstName());
            controller.employeeLastName.setText(profileModel.getLastName());
            controller.employeePhone.setText(profileModel.getPhone());
            controller.employeeEmail.setText(profileModel.getEmail());
        });
    }

    public void requestAndDisplay(
        ProfileController controller
    ) {
        Http
        .request("/api/users")
        .auth()
        .get()
        .queryParam("login", controller.getLogin())
        .onResponse(response -> {
            String body = response.body();

            OpenEmployeeProfileResponse profileResponse = ResponseUnpacker.extract(body, OpenEmployeeProfileResponse.class);

            displayData(
                profileResponse.profile,
                controller
            );
        })
        .send();
    }
}
