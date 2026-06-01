package com.dev.features.manager.UpdateTask.request;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dev.controllers.TaskEditController;
import com.dev.dto.responses.success.UpdateTaskResponse;
import com.dev.services.Http;
import com.dev.services.JsonEncoder;
import com.dev.services.ResponseUnpacker;
import com.fasterxml.jackson.core.JsonProcessingException;


public class UpdateTaskRequest {
    private static UpdateTaskRequest instance = null;

    private UpdateTaskRequest() {}

    public static UpdateTaskRequest getInstance() {
        if (instance == null) {
            instance = new UpdateTaskRequest();
        }
        return instance;
    }

    public void requestAndDisplay(
        TaskEditController controller
    ) throws JsonProcessingException {

        Double price = Double.parseDouble(controller.taskPrice.getText()) * 100;
        Long priceLong = Math.round(price);

        String requestBody = JsonEncoder.encode(new com.dev.dto.requests.UpdateTaskRequest(
            controller.getTaskId(),
            controller.taskTitle.getText(),
            controller.taskDescription.getText(),
            priceLong,
            LocalDateTime.of(controller.taskDeadline.getValue(), LocalTime.now()),
            controller.statusesList.getValue(),
            controller.respManagersList.getValue(),
            controller.clientsList.getValue(),
            controller.getVersion(),
            controller.checkedEmployees
        ));

        Http
        .request("/api/tasks/update")
        .post(requestBody)
        .auth()
        .onResponse(response -> {
            String body = response.body();

            if (response.statusCode() == 200) {
                UpdateTaskResponse updateResponse = ResponseUnpacker.extract(body, UpdateTaskResponse.class);

                controller.setVersion(updateResponse.version);
                controller.showSuccessUpdateModal();
            }
        })
        .send();
    }
}
