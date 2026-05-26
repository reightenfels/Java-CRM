package com.dev.features.manager.OpenTaskCard.request;

import com.dev.controllers.TaskEditController;
import com.dev.dto.models.TaskModel;
import com.dev.dto.responses.success.OpenTaskCardResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;

public class OpenTaskCardRequest {
    private static OpenTaskCardRequest instance = null;

    private OpenTaskCardRequest() {}

    public static OpenTaskCardRequest getInstance() {
        if (instance == null) {
            instance = new OpenTaskCardRequest();
        }
        return instance;
    }

    public void displayTask(
        TaskModel task,
        TaskEditController controller
    ) {
        Platform.runLater(() -> {
            controller.taskTitleLabel.setText(task.getTitle());
            controller.taskTitle.setText(task.getTitle());
            controller.taskDescription.setText(task.getDescription());
            controller.taskPrice.setText(String.valueOf(task.getPrice()));
            controller.taskDeadline.setValue(task.getDeadline().toLocalDate());
            controller.statusesList.setValue(task.getStatusDisplayName());
            controller.respManagersList.setValue(task.getManagerLogin());
        });
    }

    public void requestAndDisplay(
        TaskEditController controller
    ) {
        Long taskId = controller.getTaskId();

        Http
        .request("/api/tasks/" + taskId)
        .auth()
        .get()
        .onResponse(response -> {
            String body = response.body();
            System.out.println(body);

            OpenTaskCardResponse taskResponse = ResponseUnpacker.extract(body, OpenTaskCardResponse.class);

            displayTask(
                taskResponse.task,
                controller
            );
        })
        .send();
    }
}
