package com.dev.dto.models;

import java.time.LocalDateTime;

public class TaskModel {
    private Long id;
    private String title;
    private Long price;
    private LocalDateTime deadline;
    private String managerLogin; 
    private String clientLogin;
    private Long statusId;

    public TaskModel() {}

    public TaskModel(
        Long id, 
        String title, 
        Long price, 
        LocalDateTime deadline, 
        String managerLogin, 
        String clientLogin,
        Long statusId
    ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.deadline = deadline;
        this.managerLogin = managerLogin;
        this.clientLogin = clientLogin;
        this.statusId = statusId;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Long getPrice() { return price; }
    public LocalDateTime getDeadline() { return deadline; }
    public String getManagerLogin() { return managerLogin; }
    public String getClientLogin() { return clientLogin; }
    public Long getStatusId() { return statusId; }
}
