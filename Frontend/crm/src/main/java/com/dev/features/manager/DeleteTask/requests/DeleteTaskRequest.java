package com.dev.features.manager.DeleteTask.requests;

import com.dev.controllers.TaskEditController;
import com.dev.services.Http;
import com.dev.services.JsonEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DeleteTaskRequest {
    private static DeleteTaskRequest instance = null;

    private DeleteTaskRequest() {}

    public static DeleteTaskRequest getInstance() {
        if (instance == null) {
            instance = new DeleteTaskRequest();
        }
        return instance;
    }

    public void request(
        TaskEditController controller
    ) throws JsonProcessingException {
        String requestBody = JsonEncoder.encode(
            new com.dev.dto.requests.DeleteTaskRequest(
                controller.getVersion()
            )
        );

        System.out.println(controller.getTaskId());

        Http
        .request("/api/tasks/" + controller.getTaskId())
        .auth()
        .delete(requestBody)
        .onResponse(response -> {
            String body = response.body();

            System.out.println(body);

            controller.goBackBtnClick();
        })
        .send();
    }
}
