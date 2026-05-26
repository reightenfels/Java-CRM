package com.dev.features.employee.GetTasksList.requests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.dev.controllers.parts.TaskColumnController;
import com.dev.dto.models.StatusModel;
import com.dev.dto.responses.success.GetTasksListStatusesResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class GetTasksListStatusesRequest {
    private static GetTasksListStatusesRequest instance = null;

    private GetTasksListStatusesRequest() {}

    public static GetTasksListStatusesRequest getInstance() {
        if (instance == null) {
            instance = new GetTasksListStatusesRequest();
        }
        return instance;
    }

    public void addStatusesColumns(
        List<StatusModel> statusesList,
        Map<Long, TaskColumnController> columnsMap,
        HBox statusesColumnsHBox
    ) throws IOException {
        try {
            for (int i = 0; i < statusesList.size(); i++) {
                StatusModel status = statusesList.get(i);

                FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/views/parts/task-column.fxml")
                );
                Region columnRoot = loader.load();

                TaskColumnController controller = loader.getController();
                controller.setSystemName(status.systemName);
                controller.setTitle(status.displayName);

                columnsMap.put(status.id, controller);

                final int index = i;

                Platform.runLater(() -> {
                    statusesColumnsHBox.getChildren().add(columnRoot);

                    if (index < statusesList.size() - 1) { 
                        Region spacer = new Region();
                        spacer.setPrefWidth(225);
                        statusesColumnsHBox.getChildren().add(spacer);
                    }
                });
            }
            
        } catch (IOException e) {
        }    
    }

    public void requestAndDisplay(
        Map<Long, TaskColumnController> columnsMap,
        HBox statusesColumnsHBox,
        Runnable onSuccess
    ) {
        Http
        .request("/api/status/all")
        .auth()
        .get()
        .onResponse(response -> {
            String body = response.body();

            GetTasksListStatusesResponse statusesResponse = ResponseUnpacker.extract(body, GetTasksListStatusesResponse.class);
            
            try {
                addStatusesColumns(
                    statusesResponse.statusesList,
                    columnsMap,
                    statusesColumnsHBox
                );

                if (onSuccess != null) onSuccess.run(); 
            } catch (IOException e) {

            }
        })
        .send();
    }
}
