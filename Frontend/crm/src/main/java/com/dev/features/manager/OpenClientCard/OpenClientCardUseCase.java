package com.dev.features.manager.OpenClientCard;

import com.dev.controllers.ClientController;
import com.dev.features.manager.OpenClientCard.requests.OpenClientCardRequest;

public class OpenClientCardUseCase {
    public static void execute(
        ClientController controller
    ) {
        OpenClientCardRequest.getInstance().requestAndDisplay(controller);
    }
}
