package com.dev.features.manager.CreateTask;

import com.dev.controllers.TaskCreateController;
import com.dev.features.manager.CreateTask.requests.CreateTaskRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CreateTaskUseCase {
    public static void execute(
        TaskCreateController controller
    ) throws JsonProcessingException {
        CreateTaskRequest.getInstance().requestAndDisplay(
            controller
        );
    }
}
