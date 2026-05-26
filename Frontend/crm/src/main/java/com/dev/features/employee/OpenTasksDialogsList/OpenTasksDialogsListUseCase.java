package com.dev.features.employee.OpenTasksDialogsList;

import com.dev.dto.models.DialogModel;
import com.dev.features.employee.OpenTasksDialogsList.requests.OpenTasksDialogsListRequest;

import javafx.collections.ObservableList;

public class OpenTasksDialogsListUseCase {
    public static void execute(ObservableList<DialogModel> dialogsObsList) {
        OpenTasksDialogsListRequest.getInstance().requestAndDisplay(dialogsObsList);
    }
}
