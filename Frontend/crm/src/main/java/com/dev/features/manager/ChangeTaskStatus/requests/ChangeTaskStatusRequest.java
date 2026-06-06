package com.dev.features.manager.ChangeTaskStatus.requests;

import com.dev.services.Http;
import com.dev.services.JsonEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ChangeTaskStatusRequest {
    private static ChangeTaskStatusRequest instance = null;

    private ChangeTaskStatusRequest() {}

    public static ChangeTaskStatusRequest getInstance() {
        if (instance == null) {
            instance = new ChangeTaskStatusRequest();
        }
        return instance;
    }

    public void request(
        String statusSystemName,
        Long taskId,
        int version
    ) throws JsonProcessingException {
        String requestString = JsonEncoder.encode(new com.dev.dto.requests.ChangeTaskStatusRequest(
            statusSystemName,
            version
        ));

        Http
        .request("/api/tasks/" + taskId + "/status")
        .auth()
        .post(requestString)
        .onResponse(response -> {
            String body = response.body();

            System.out.println(body);
        })
        .send();
    }
}
