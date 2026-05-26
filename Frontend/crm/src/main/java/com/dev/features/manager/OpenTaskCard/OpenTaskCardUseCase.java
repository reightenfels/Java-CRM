package com.dev.features.manager.OpenTaskCard;

import com.dev.controllers.TaskEditController;
import com.dev.features.manager.OpenTaskCard.request.OpenTaskCardManagersRequest;
import com.dev.features.manager.OpenTaskCard.request.OpenTaskCardRequest;
import com.dev.features.manager.OpenTaskCard.request.OpenTaskCardStatusesRequest;

public class OpenTaskCardUseCase {
    public static void execute(
        TaskEditController controller
    ) {
        OpenTaskCardStatusesRequest.getInstance().requestAndDisplay(
            controller.statusesObsList,
            () -> OpenTaskCardManagersRequest.getInstance().requestAndDisplay(
                controller.managersObsList,
                () -> OpenTaskCardRequest.getInstance().requestAndDisplay(controller)
            )
        );
    }
}
