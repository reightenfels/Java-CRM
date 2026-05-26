package com.dev.features.employee.SendMessageToTask.requests;

import com.dev.services.Http;
import com.dev.services.JsonEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SendMessageToTaskRequest {
    private static SendMessageToTaskRequest instance = null;

    private SendMessageToTaskRequest() {}

    public static SendMessageToTaskRequest getInstance() {
        if (instance == null) {
            instance = new SendMessageToTaskRequest();
        }
        return instance;
    }

    public void requestAndDisplay(
        com.dev.dto.requests.SendMessageToTaskRequest request
    ) {
        try {
            Http
            .request("/api/messages/tasks/send")
            .auth()
            .post(JsonEncoder.encode(request))
            .onResponse(response -> {
                String body = response.body();

                System.out.println(body);
            })
            .send();
        } catch (JsonProcessingException e) {
        }
        
    }
}
