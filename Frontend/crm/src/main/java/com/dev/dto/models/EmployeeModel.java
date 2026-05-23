package com.dev.dto.models;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeModel {
    private final SimpleStringProperty photo;
    private final SimpleStringProperty fio;
    private final SimpleStringProperty login;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;

    public EmployeeModel(String photo, String fio, String login, String email, String phone) {
        this.photo = new SimpleStringProperty(photo);
        this.fio = new SimpleStringProperty(fio);
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    // Геттеры
    public String getPhoto() { return photo.get(); }
    public String getFio() { return fio.get(); }
    public String getLogin() { return login.get(); }
    public String getEmail() { return email.get(); }
    public String getPhone() { return phone.get(); }

    public SimpleStringProperty photoProperty() { return photo; }
    public SimpleStringProperty fioProperty() { return fio; }
    public SimpleStringProperty loginProperty() { return login; }
    public SimpleStringProperty emailProperty() { return email; }
    public SimpleStringProperty phoneProperty() { return phone; }
}
