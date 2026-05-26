package com.dev.features.manager.OpenTaskCard.request;

import java.util.List;

import com.dev.dto.models.StatusModel;
import com.dev.dto.responses.success.GetTasksListStatusesResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OpenTaskCardStatusesRequest {
    private static OpenTaskCardStatusesRequest instance = null;

    private OpenTaskCardStatusesRequest() {}

    public static OpenTaskCardStatusesRequest getInstance() {
        if (instance == null) {
            instance = new OpenTaskCardStatusesRequest();
        }
        return instance;
    }

    public void addStatuses(
        List<StatusModel> statusesList,
        ObservableList<String> statusesObsList
    ) {
        for(int i = 0; i < statusesList.size(); i++) {
            final int index = i;

            Platform.runLater(() -> {
                statusesObsList.add(statusesList.get(index).displayName);
            });
        }
    }

    public void requestAndDisplay(
        ObservableList<String> statusesObsList,
        Runnable onSuccess
    ) {
        Http
        .request("/api/status/all")
        .auth()
        .get()
        .onResponse(response -> {
            String body = response.body();

            GetTasksListStatusesResponse statusesResponse = ResponseUnpacker.extract(body, GetTasksListStatusesResponse.class);
            
            addStatuses(
                statusesResponse.statusesList,
                statusesObsList
            );

            if (onSuccess != null) onSuccess.run(); 

        })
        .send();
    }
}
