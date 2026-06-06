package com.dev.features.manager.DeleteTask;

import com.dev.controllers.TaskEditController;
import com.dev.features.manager.DeleteTask.requests.DeleteTaskRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DeleteTaskUseCase {
    public static void execute(
        TaskEditController controller
    ) throws JsonProcessingException {
        DeleteTaskRequest.getInstance().request(controller);
    }
}
