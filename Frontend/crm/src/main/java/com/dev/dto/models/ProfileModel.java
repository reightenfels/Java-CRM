package com.dev.dto.models;

public class ProfileModel {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public ProfileModel() {}

    public ProfileModel(String email, String firstName, String lastName, String phone) {
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
}
