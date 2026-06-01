package com.dev.features.manager.OpenTaskCard.request;

import java.util.Set;

import com.dev.dto.responses.success.OpenTaskCardClientsResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OpenTaskCardClientsRequest {
    private static OpenTaskCardClientsRequest instance = null;

    private OpenTaskCardClientsRequest() {}

    public static OpenTaskCardClientsRequest getInstance() {
        if (instance == null) {
            instance = new OpenTaskCardClientsRequest();
        }
        return instance;
    }

    public void addStatuses(
        Set<String> clientsList,
        ObservableList<String> clientsObsList
    ) {
        Platform.runLater(() -> {
            clientsObsList.addAll(clientsList);
        });
    }

    public void requestAndDisplay(
        ObservableList<String> clientsObsList,
        Runnable onSuccess
    ) {
        Http
        .request("/api/client/logins")
        .auth()
        .get()
        .onResponse(response -> {
            String body = response.body();

            OpenTaskCardClientsResponse clientsResponse = ResponseUnpacker.extract(body, OpenTaskCardClientsResponse.class);
            
            addStatuses(
                clientsResponse.clients,
                clientsObsList
            );

            if (onSuccess != null) onSuccess.run(); 

        })
        .send();
    }
}
