package com.dev.dto.models;

import java.time.LocalDateTime;

public class TaskCardModel {
    private Long id;
    private String title;
    private Long price;
    private LocalDateTime deadline;
    private String managerLogin; 
    private String clientLogin;
    private Long statusId;
    private Long employeeDialogId;
    private int version;

    public TaskCardModel() {}

    public TaskCardModel(
        Long id, 
        String title, 
        Long price, 
        LocalDateTime deadline, 
        String managerLogin, 
        String clientLogin,
        Long statusId,
        Long employeeDialogId,
        int version
    ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.deadline = deadline;
        this.managerLogin = managerLogin;
        this.clientLogin = clientLogin;
        this.statusId = statusId;
        this.employeeDialogId = employeeDialogId;
        this.version = version;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Long getPrice() { return price; }
    public LocalDateTime getDeadline() { return deadline; }
    public String getManagerLogin() { return managerLogin; }
    public String getClientLogin() { return clientLogin; }
    public Long getStatusId() { return statusId; }

    public Long getEmployeeDialogId() {
        return employeeDialogId;
    }

    public int getVersion() {
        return version;
    }
}
