package com.dev.features.manager.OpenTaskCard.request;

import java.util.List;

import com.dev.dto.responses.success.OpenTaskCardEmployeesResponse;
import com.dev.services.Http;
import com.dev.services.ResponseUnpacker;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class OpenTaskCardEmployeesRequest {
    private static OpenTaskCardEmployeesRequest instance = null;

    private OpenTaskCardEmployeesRequest() {}

    public static OpenTaskCardEmployeesRequest getInstance() {
        if (instance == null) {
            instance = new OpenTaskCardEmployeesRequest();
        }
        return instance;
    }

    public void addEmployees(
        List<String> employeesList,
        ObservableList<String> employeesObsList
    ) {
        for(int i = 0; i < employeesList.size(); i++) {
            final int index = i;

            Platform.runLater(() -> {
                employeesObsList.add(employeesList.get(index));
            });
        }
    }

    public void requestAndDisplay(
        ObservableList<String> employeesObsList,
        Runnable runnable
    ) {
        Http
        .request("/api/employees/logins")
        .get()
        .auth()
        .onResponse(response -> {
            String body = response.body();

            OpenTaskCardEmployeesResponse employeesResponse = ResponseUnpacker.extract(body, OpenTaskCardEmployeesResponse.class);

            addEmployees(
                employeesResponse.employees,
                employeesObsList
            );

            if (runnable != null) runnable.run();
        })
        .send();
    }
}
