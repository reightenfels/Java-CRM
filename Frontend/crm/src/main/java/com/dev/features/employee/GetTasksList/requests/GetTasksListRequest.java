package com.dev.features.employee.GetTasksList.requests;

import java.util.List;

import com.dev.controllers.TasksListController;
import com.dev.controllers.parts.TaskColumnController;
import com.dev.dto.models.TaskCardModel;
import com.dev.dto.responses.success.GetTasksListResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;

public class GetTasksListRequest {
    private static GetTasksListRequest instance = null;

    private GetTasksListRequest() {}

    public static GetTasksListRequest getInstance() {
        if (instance == null) {
            instance = new GetTasksListRequest();
        }
        return instance;
    }

    public void addTasks(
        List<TaskCardModel> tasks,
        TasksListController controller
    ) {
        Platform.runLater(() -> {
            for (TaskCardModel task: tasks) {
                Long statusId = task.getStatusId();
                TaskColumnController columnController = controller.columnsMap.get(statusId);

                if (columnController != null) {
                    columnController.addTask(task);
                    controller.tasksCardsMap.put(task.getId(), task);
                    columnController.setTasksListController(controller);
                } else {
                    System.err.println("Колонка для статуса " + task.getStatusId() + " не найдена");
                }
            }
        });
    }

    public void requestAndDisplay(
        TasksListController controller
    ) {
        Http
        .request("/api/employee/tasks")
        .auth()
        .get()
        .onResponse(response -> {
            String body = response.body();

            GetTasksListResponse tasksResponse = ResponseUnpacker.extract(body, GetTasksListResponse.class);
            
            addTasks(
                tasksResponse.tasks,
                controller
            );
        })
        .send();
    }
}
