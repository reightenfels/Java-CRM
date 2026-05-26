package com.dev.features.employee.GetTasksList;

import java.util.Map;

import com.dev.controllers.parts.TaskColumnController;
import com.dev.features.employee.GetTasksList.requests.GetTasksListRequest;
import com.dev.features.employee.GetTasksList.requests.GetTasksListStatusesRequest;

import javafx.scene.layout.HBox;

public class GetTasksListUseCase {
    public static void execute(
        Map<Long, TaskColumnController> columnsMap,
        HBox statusesColumnsHBox
    ) {
        GetTasksListStatusesRequest.getInstance().requestAndDisplay(
            columnsMap,
            statusesColumnsHBox,
            () -> GetTasksListRequest.getInstance().requestAndDisplay(columnsMap)
        );
    }
}
