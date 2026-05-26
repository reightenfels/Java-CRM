package com.dev.features.employee.OpenTaskDialog.requests;

import java.util.List;

import com.dev.dto.models.message.ChatItem;
import com.dev.dto.models.message.MessageModel;
import com.dev.dto.responses.success.OpenTaskDialogResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OpenTaskDialogRequest {
    private static OpenTaskDialogRequest instance = null;

    private OpenTaskDialogRequest() {}

    public static OpenTaskDialogRequest getInstance() {
        if (instance == null) {
            instance = new OpenTaskDialogRequest();
        }
        return instance;
    }

    public void addMessages(
        List<MessageModel> messages,
        ObservableList<ChatItem> messagesObsList
    ) {
        Platform.runLater(() -> {
            messagesObsList.addAll(messages);
        });
    }

    public void requestAndDisplay(
        Long dialogId,
        ObservableList<ChatItem> messagesObsList
    ) {
        Http
        .request("/api/dialogs/tasks/" + dialogId)
        .auth()
        .get()
        .onResponse(response -> {
            String body = response.body();
            
            OpenTaskDialogResponse statusesResponse = ResponseUnpacker.extract(body, OpenTaskDialogResponse.class);

            addMessages(statusesResponse.messages, messagesObsList);
        })
        .send();
    }
}
