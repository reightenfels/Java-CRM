package com.dev.features.employee.OpenTasksDialogsList;

import com.dev.controllers.EmployeeChatsController;
import com.dev.features.employee.OpenTasksDialogsList.requests.OpenTasksDialogsListRequest;

public class OpenTasksDialogsListUseCase {
    public static void execute(EmployeeChatsController controller) {
        OpenTasksDialogsListRequest.getInstance().requestAndDisplay(
            controller,
            () -> {
                controller.selectDefaultDialogById();
            }
        );
    }
}
