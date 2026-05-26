package com.dev.features.employee.SendMessageToTask;

import com.dev.features.employee.SendMessageToTask.requests.SendMessageToTaskRequest;

public class SendMessageToTaskUseCase {
    public static void execute(
        com.dev.dto.requests.SendMessageToTaskRequest request
    ) {
        SendMessageToTaskRequest.getInstance().requestAndDisplay(request);
    }
}
