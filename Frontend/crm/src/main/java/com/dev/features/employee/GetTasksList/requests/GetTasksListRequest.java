package com.dev.features.employee.GetTasksList.requests;

import java.util.List;
import java.util.Map;

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
        Map<Long, TaskColumnController> columnsMap
    ) {
        Platform.runLater(() -> {
            for (TaskCardModel task: tasks) {
                Long statusId = task.getStatusId();
                TaskColumnController columnController = columnsMap.get(statusId);

                if (columnController != null) {
                    columnController.addTask(task);
                } else {
                    System.err.println("Колонка для статуса " + task.getStatusId() + " не найдена");
                }
            }
        });
    }

    public void requestAndDisplay(
        Map<Long, TaskColumnController> columnsMap
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
                columnsMap
            );
        })
        .send();
    }
}
