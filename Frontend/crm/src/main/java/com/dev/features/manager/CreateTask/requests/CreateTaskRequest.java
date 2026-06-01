package com.dev.features.manager.CreateTask.requests;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dev.controllers.TaskCreateController;
import com.dev.services.Http;
import com.dev.services.JsonEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CreateTaskRequest {
    private static CreateTaskRequest instance = null;

    private CreateTaskRequest() {}

    public static CreateTaskRequest getInstance() {
        if (instance == null) {
            instance = new CreateTaskRequest();
        }
        return instance;
    }

    public void requestAndDisplay(
        TaskCreateController controller
    ) throws JsonProcessingException {

        Double price = Double.parseDouble(controller.taskPrice.getText()) * 100;
        Long priceLong = Math.round(price);

        String requestBody = JsonEncoder.encode(new com.dev.dto.requests.CreateTaskRequest(
            controller.taskTitle.getText(),
            controller.taskDescription.getText(),
            priceLong,
            LocalDateTime.of(controller.taskDeadline.getValue(), LocalTime.now()),
            controller.statusesList.getValue(),
            controller.respManagersList.getValue(),
            controller.clientsList.getValue(),
            controller.checkedEmployees
        ));

        Http
        .request("/api/tasks/create")
        .post(requestBody)
        .auth()
        .onResponse(response -> {
            System.out.println(response.body());
            if (response.statusCode() == 200) {
                controller.showSuccessUpdateModal();
            }
        })
        .send();
    }
}
