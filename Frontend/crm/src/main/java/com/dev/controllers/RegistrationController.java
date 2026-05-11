package com.dev.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {
    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField firstName;

    @FXML 
    private TextField lastName;

    @FXML
    private TextField phone;

    public void registrationClick() {
        System.out.println("clicked");
    }
}
