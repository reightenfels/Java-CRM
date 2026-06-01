package com.dev.features.manager.OpenCreateTaskCard;

import com.dev.controllers.TaskCreateController;
import com.dev.features.manager.OpenTaskCard.request.OpenTaskCardClientsRequest;
import com.dev.features.manager.OpenTaskCard.request.OpenTaskCardEmployeesRequest;
import com.dev.features.manager.OpenTaskCard.request.OpenTaskCardManagersRequest;
import com.dev.features.manager.OpenTaskCard.request.OpenTaskCardStatusesRequest;

public class OpenCreateTaskCardUseCase {
    public static void execute(
        TaskCreateController controller
    ) {
        OpenTaskCardStatusesRequest.getInstance().requestAndDisplay(
            controller.statusesObsList,
            () -> OpenTaskCardManagersRequest.getInstance().requestAndDisplay(
                controller.managersObsList,
                () -> OpenTaskCardEmployeesRequest.getInstance().requestAndDisplay(
                    controller.employeesObsList,
                    () -> OpenTaskCardClientsRequest.getInstance().requestAndDisplay(
                        controller.clientsObsList, 
                        () -> {}
                    )
                )
            )
        );
    }
}
