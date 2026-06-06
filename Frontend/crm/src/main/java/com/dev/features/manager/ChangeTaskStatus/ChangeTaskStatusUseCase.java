package com.dev.features.manager.ChangeTaskStatus;

import com.dev.features.manager.ChangeTaskStatus.requests.ChangeTaskStatusRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ChangeTaskStatusUseCase {
    public static void execute(
        String statusSystemName,
        Long taskId,
        int version
    ) throws JsonProcessingException {
        ChangeTaskStatusRequest.getInstance().request(
            statusSystemName,
            taskId,
            version
        );
    }
}
