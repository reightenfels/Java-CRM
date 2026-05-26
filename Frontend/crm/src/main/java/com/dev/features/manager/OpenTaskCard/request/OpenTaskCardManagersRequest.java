package com.dev.features.manager.OpenTaskCard.request;

import java.util.List;

import com.dev.dto.responses.success.OpenTaskCardManagersResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OpenTaskCardManagersRequest {
    private static OpenTaskCardManagersRequest instance = null;

    private OpenTaskCardManagersRequest() {}

    public static OpenTaskCardManagersRequest getInstance() {
        if (instance == null) {
            instance = new OpenTaskCardManagersRequest();
        }
        return instance;
    }

    public void addManagers(
        List<String> managersList,
        ObservableList<String> managersObsList
    ) {
        for(int i = 0; i < managersList.size(); i++) {
            final int index = i;

            Platform.runLater(() -> {
                managersObsList.add(managersList.get(index));
            });
        }
    }

    public void requestAndDisplay(
        ObservableList<String> managersObsList,
        Runnable runnable
    ) {
        Http
        .request("/api/managers/logins")
        .get()
        .auth()
        .onResponse(response -> {
            String body = response.body();
            System.out.println(body);

            OpenTaskCardManagersResponse managersResponse = ResponseUnpacker.extract(body, OpenTaskCardManagersResponse.class);

            addManagers(
                managersResponse.managers,
                managersObsList
            );

            if (runnable != null) runnable.run();
        })
        .send();
    }
}
