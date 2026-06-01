package com.dev.dto.models;

public class ClientProfileModel {
    private String login;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public ClientProfileModel() {}

    public ClientProfileModel(String login, String email, String firstName, String lastName, String phone) {
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }
}
