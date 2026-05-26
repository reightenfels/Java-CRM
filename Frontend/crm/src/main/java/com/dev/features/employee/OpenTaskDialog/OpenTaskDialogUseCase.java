package com.dev.features.employee.OpenTaskDialog;

import com.dev.dto.models.message.ChatItem;
import com.dev.features.employee.OpenTaskDialog.requests.OpenTaskDialogRequest;

import javafx.collections.ObservableList;

public class OpenTaskDialogUseCase {
    public static void execute(
        Long dialogId,
        ObservableList<ChatItem> messagesObsList
    ) {
        OpenTaskDialogRequest.getInstance().requestAndDisplay(dialogId, messagesObsList);
    }
}
