package com.dev.features.manager.UpdateTask;

import com.dev.controllers.TaskEditController;
import com.dev.features.manager.UpdateTask.request.UpdateTaskRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public class UpdateTaskUseCase {
    public static void execute(
        TaskEditController controller
    ) throws JsonProcessingException {
        UpdateTaskRequest.getInstance().requestAndDisplay(controller);
    }
}
