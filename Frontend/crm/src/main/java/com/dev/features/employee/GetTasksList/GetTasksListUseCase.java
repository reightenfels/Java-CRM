package com.dev.features.employee.GetTasksList;

import com.dev.controllers.TasksListController;
import com.dev.features.employee.GetTasksList.requests.GetTasksListRequest;
import com.dev.features.employee.GetTasksList.requests.GetTasksListStatusesRequest;

public class GetTasksListUseCase {
    public static void execute(
        TasksListController controller
    ) {
        GetTasksListStatusesRequest.getInstance().requestAndDisplay(
            controller.columnsMap,
            controller.statusesColumnsHBox,
            () -> GetTasksListRequest.getInstance().requestAndDisplay(controller)
        );
    }
}
