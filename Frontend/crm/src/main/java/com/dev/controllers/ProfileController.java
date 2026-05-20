package com.dev.controllers;

import com.dev.services.Navigation;

public class ProfileController {
    public void goBackBtnClick() {
        Navigation.getInstance().loadAndShowBack();
    }
}
