package com.dev.dto.models;

import java.time.LocalDateTime;
import java.util.Set;

public class TaskModel {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private LocalDateTime deadline;
    private String statusDisplayName;
    private String managerLogin; 
    private String clientLogin;
    private int version;
    private Set<String> employees;

    public TaskModel() {}

    public TaskModel(
        Long id, 
        String title, 
        String description,
        Long price, 
        LocalDateTime deadline, 
        String statusDisplayName,
        String managerLogin, 
        String clientLogin,
        int version,
        Set<String> employees
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.deadline = deadline;
        this.statusDisplayName = statusDisplayName;
        this.managerLogin = managerLogin;
        this.clientLogin = clientLogin;
        this.version = version;
        this.employees = employees;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Long getPrice() { return price; }
    public LocalDateTime getDeadline() { return deadline; }
    public String getStatusDisplayName() { return statusDisplayName; }
    public String getManagerLogin() { return managerLogin; }
    public String getClientLogin() { return clientLogin; }
    public int getVersion() { return version; }
    public Set<String> getEmployees() { return employees; }
}
