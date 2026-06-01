package com.dev.features.employee.OpenEmployeeProfile;

import com.dev.controllers.ProfileController;
import com.dev.features.employee.OpenEmployeeProfile.requests.OpenEmployeeProfileRequest;

public class OpenEmployeeProfileUseCase {
    public static void execute(
        ProfileController controller
    ) {
        OpenEmployeeProfileRequest.getInstance().requestAndDisplay(controller);
    }
}
