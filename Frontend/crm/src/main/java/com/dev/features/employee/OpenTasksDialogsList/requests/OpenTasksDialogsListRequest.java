package com.dev.features.employee.OpenTasksDialogsList.requests;

import java.util.List;

import com.dev.dto.models.DialogModel;
import com.dev.dto.responses.success.OpenTasksDialogsListResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OpenTasksDialogsListRequest {
    private static OpenTasksDialogsListRequest instance = null;

    private OpenTasksDialogsListRequest() {}

    public static OpenTasksDialogsListRequest getInstance() {
        if (instance == null) {
            instance = new OpenTasksDialogsListRequest();
        }
        return instance;
    }

    public void addDialogs(
        List<DialogModel> dialogs,
        ObservableList<DialogModel> dialogsObsList
    ) {
        Platform.runLater(() -> {
            dialogsObsList.addAll(dialogs);
        });
    }

    public void requestAndDisplay(
        ObservableList<DialogModel> dialogsObsList
    ) {
        Http
        .request("/api/employee/dialogs/tasks")
        .get()
        .auth()
        .onResponse(response -> {
            String body = response.body();

            OpenTasksDialogsListResponse dialogsResponse = ResponseUnpacker.extract(body, OpenTasksDialogsListResponse.class);

            List<DialogModel> dialogs = dialogsResponse.dialogs;

            addDialogs(dialogs, dialogsObsList);
        })
        .send();
    }
}
